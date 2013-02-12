package edu.msoe.se2800.h4.control;


import java.util.List;

import edu.msoe.se2800.h4.RobotControllerLejos;

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
	
	static RobotControllerLejos test = new RobotControllerLejos();
	public static void main(String[] args){
		Path path = new Path();
		path.add(new Waypoint(6,-6));
		test.setPath(path);
		test.followRoute();
	}
	
}
