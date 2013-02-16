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
public class RobotControllerLejos implements IRobotController {

	private Path forward = new Path();
	private boolean check = false;
	/**
	 * The pilot class is set to 5.6 cm wheel diameter and 16.8 cm between the wheels
	 */
	private DifferentialPilot pilot;
	/**
	 * The Navigator is used to follow a path or by point.
	 */
	private Navigator nav;
	/**
	 * The path to be followed by the robot.
	 */
	private Path path;

	/**
	 * The constructor instantiates the pilot and navigator. Single step is
	 * false.
	 */
	public RobotControllerLejos() {
		path = new Path();
		pilot = new DifferentialPilot(5.6, 18, Motor.A, Motor.B);
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
		nav.clearPath();
		for (int i = 0; i < path.size(); i++) {
			forward.add(new Waypoint(path.get(i)));
			this.path.add(new Waypoint(path.get(i)));
		}
		for (int i = path.size() - 2; i > -1; i--) {
			this.path.add(new Waypoint(path.get(i)));
		}

		this.path.add(new Waypoint(0, 0));
		
		for(Waypoint wp : this.path){
			nav.addWaypoint(wp);
		}
		

	}

	/**
	 * Follow the route
	 */
	@Override
	public void followRoute() {

		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				if (check == false) {
					nav.singleStep(false);
				}
				nav.followPath();
				nav.waitForStop();
				if (nav.pathCompleted()) {
					// This has to be reset since path is reset in navigator
					// after
					// the
					// followPath is called
					for (Waypoint wp : path) {
						nav.addWaypoint(wp);
					}
				}
				nav.rotateTo(0);
			}

		});
		t.start();
	}
	

	

	@Override
	public void addWaypoint(Waypoint wp) {
		forward.add(wp);
		Path temp = new Path();
		for (Waypoint way : forward) {
			temp.add(way);
		}
		this.setPath(temp);
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
				nav.waitForStop();
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
        Logger.INSTANCE.log(this.getClass().getSimpleName(), "Emergency stopped");
	}

	@Override
	public double getVelocity() {
		return pilot.getTravelSpeed();
	}

}
