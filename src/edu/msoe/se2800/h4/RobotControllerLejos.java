
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

public class RobotControllerLejos implements IRobotController {
	private DifferentialPilot pilot;
	private static Navigator nav;
	private static Path path;
	
	public RobotControllerLejos(){
		path = new Path();
		pilot = new DifferentialPilot(2,7,Motor.A,Motor.B);
		nav  = new Navigator(pilot);
		nav.singleStep(false);	
	}
	@Override
	public void setPath(Path path){
		this.path = path;
		nav.setPath(path);
	}
	@Override
	public void followRoute(){
		reverse();
		forward();
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
		int size = path.size();
		for(int i = size-2; i>-1; i--){
			path.add(path.get(i));
			
		}
		path.add(new Waypoint(0, 0));
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
		Waypoint wp = nav.getWaypoint();
		path.indexOf(wp);
		

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
