package edu.msoe.se2800.h4.control;


import java.util.List;

//import edu.msoe.se2800.h4.Path;
import lejos.nxt.Motor;
import lejos.nxt.Sound;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Waypoint;
//import edu.msoe.se2800.h4.jplot.JPoint;
import lejos.robotics.navigation.Pose;
import lejos.robotics.pathfinding.Path;

/**
 * @author hornick
 * This simple application demonstrates how to move the robot.
 *
 */
public class RobotDemoApp {
//	
//	static int x;
//	static int y;
//	static double length;
//	static double angle;
//	
	//private static DifferentialPilot pilot = new DifferentialPilot(2.215, 2.125, Motor.B, Motor.C, false);
	
	//private List<JPoint> points;
	
	/**
	 * @param args not used
	 */
	public static void main(String[] args) {
		Robot kevin = new Robot();
		Path path = new Path();
		path.add(new Waypoint(-2,-2));
		//path.add(new Waypoint(5,5));
		path.add(new Waypoint(-1,1));
		kevin.setPoints(path);
		kevin.travelPath();
	}
	/*
	public void setPoints(Path path){
		points = path.getPoints();
	}
	*/
	
	
	
}
