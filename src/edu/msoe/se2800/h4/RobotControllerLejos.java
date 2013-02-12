
package edu.msoe.se2800.h4;

import java.awt.Point;

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
 * @author koenigj
 *
 */
public class RobotControllerLejos implements IRobotController {
	private boolean stop = false;
	/**
	 * The pilot class is set to 2 cm wheel diameter and 7 cm between the wheels
	 */
	private static DifferentialPilot pilot;
	/**
	 * The Navigator is used to follow a path or by point.
	 */
	private static Navigator nav;
	/**
	 * The path to be followed by the robot.
	 */
	private static Path path;
	/**
	 * The constructor instantiates the pilot and navigator. Single step is false.
	 */
	public RobotControllerLejos(){
		path = new Path();
		pilot = new DifferentialPilot(2,7,Motor.A,Motor.B);
		nav  = new Navigator(pilot);
		nav.singleStep(false);	
	}
	/**
	 * Set the path to be followed.
	 */
	@Override
	public void setPath(Path path){
		this.path = path;
		nav.setPath(path);
	}
	/**
	 * Follow the route 
	 */
	@Override
	public void followRoute(){
			forward();
			reverse();
			nav.waitForStop();
		
	}
	@Override
	public void addWaypoint(Waypoint wp){
		path.add(wp);
	}

    @Override
    public Path getPath() {
        return nav.getPath();
    }

    public static  void forward(){
		nav.followPath();
		
	}

	public static void reverse(){
		for(int i = path.size()-2; i>-1; i--){
			nav.goTo(path.get(i));
			
		}
		nav.goTo(0, 0);
	}
	@Override
	public void goToImmediate(Waypoint wp){
		if(path != null){
			path.clear();
		}
		nav.goTo(wp);
		nav.waitForStop();
		path.clear();
	}
	@Override
	public void singleStep(boolean setting){
		nav.singleStep(setting);
	}
	@Override
	public Waypoint getLocation(){
		PoseProvider provider =  nav.getPoseProvider();
		Pose pose = provider.getPose();
		return (Waypoint)pose.getLocation();
		
	}
	@Override
	public void setVelocity(double speed){
		pilot.setTravelSpeed(speed);
	}
	@Override
	public boolean isMoving(){
		return pilot.isMoving();
	}
	@Override
	public void setReverse(boolean isReverse){
		pilot = new DifferentialPilot(2,7,Motor.A,Motor.B, isReverse);
		nav  = new Navigator(pilot);
	}
	
	
	@Override
	public void stop(){
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
