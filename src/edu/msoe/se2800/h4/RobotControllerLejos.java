package edu.msoe.se2800.h4;


import java.awt.Point;

import javax.swing.SwingUtilities;

import lejos.nxt.Motor;
import lejos.nxt.remote.RemoteMotor;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Pose;
import lejos.robotics.navigation.Waypoint;
import lejos.robotics.pathfinding.Path;

/**
 * Provides the basic functions for controlling the lejos robot.
 * 
 * @author koenigj
 * 
 */
public class RobotControllerLejos implements IRobotController{
	private Path forward = new Path();
	private boolean check = false;
	/**
	 * The pilot class is set to 2 cm wheel diameter and 7 cm between the wheels
	 */
	private DifferentialPilot pilot;
	/**
	 * The Navigator is used to follow a path or by point.
	 */
	private static Navigator nav;
	/**
	 * The path to be followed by the robot.
	 */
	private static Path path;

	/**
	 * The constructor instantiates the pilot and navigator. Single step is
	 * false.
	 */
	public RobotControllerLejos() {
		path = new Path();
		pilot = new DifferentialPilot(2, 7.5, Motor.A, Motor.B);
		nav = new Navigator(pilot);
		nav.singleStep(false);
	}

	/**
	 * Set the path to be followed.
	 */
	@Override
	public void setPath(Path path) {
        forward.clear();
        this.path.clear();
		for (int i = 0; i < path.size(); i++) {
			forward.add(new Waypoint(path.get(i)));
            this.path.add(new Waypoint(path.get(i)));
		}
		for (int i = path.size() - 2; i > -1; i--) {
			this.path.add(new Waypoint(path.get(i)));
		}
		
		this.path.add (new Waypoint(0, 0));
		nav.setPath(this.path);
		
	}

	/**
	 * Follow the route
	 */
	@Override
	public void followRoute() {
		Thread t = new Thread(new Runnable(){

			@Override
			public void run() {
				if(check == false){
					nav.singleStep(false);
				}
				nav.followPath();
				nav.waitForStop();
				// This has to be reset since path is reset in navigator after the
				// followPath is called
				for (int i = 0; i < path.size(); i++) {
					nav.addWaypoint(path.get(i));
				}

			}
			
		});
		t.start();
	}

	@Override
	public void addWaypoint(Waypoint wp) {
		forward.add(wp);
		this.setPath(forward);
	}

	@Override
	public Path getPath() {
		return forward;
	}

	

	@Override
	public void goToImmediate(Waypoint wp) {
		 if (path != null) {
	            path.clear();
	            nav.clearPath();
	        }
	        nav.addWaypoint(wp);
	        path.add(wp);

	        Thread t = new Thread(new Runnable() {
	            @Override
	            public void run() {
	                nav.followPath();
	                path.clear();
	                nav.clearPath();
	            }
	        });

	        t.start();
	}

	@Override
	public void singleStep(boolean setting) {
		check = setting;
		nav.singleStep(setting);
	}

	@Override
	public Waypoint getLocation() {
		PoseProvider provider = nav.getPoseProvider();
		Pose pose = provider.getPose();
		return (Waypoint) pose.getLocation();

	}

	@Override
	public void setVelocity(double speed) {
		pilot.setTravelSpeed(speed);
	}

	@Override
	public boolean isMoving() {
		return pilot.isMoving();
	}

	@Override
	public void setReverse(boolean isReverse) {
		pilot = new DifferentialPilot(2, 7, Motor.A, Motor.B, isReverse);
		nav = new Navigator(pilot);
	}

	@Override
	public void stop() {
		nav.singleStep(true);

	}

	@Override
	public void stopImmediate() {
		nav.stop();

	}

	@Override
	public double getVelocity() {
		return pilot.getTravelSpeed();
	}

}
