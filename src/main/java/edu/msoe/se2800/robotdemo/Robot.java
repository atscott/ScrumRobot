package edu.msoe.se2800.robotdemo;

import java.util.Observer;
import lejos.nxt.Motor;

/**
 * This class defines the various pieces of a robot. In general, a robot
 * can consist of subcomponents that are responsible for propelling the robot
 * controlling auxiliary actuators (such as claws), monitoring the battery power,
 * controlling or monitoring auxiliary devices (such as a GPS, compass, or other sensors), etc.
 * This demo code implements a robot that consists of only a subcomponent
 * (PropulsionMotorController) that is responsible for propelling the robot.
 * 
 * @author M. Hornick, based on a class originally written by Dr. Walt Schilling 
 * 
 */
public class Robot implements iRobotMotionBehavior {
	private DifferentialMotorController pmc;	

	/**
	 * This method creates a new instance of the robot and its associated subcomponents.
	 */
	public Robot() {
		try { // needed to catch null argument exception which we explicitly avoid above
		pmc = new DifferentialMotorController(Motor.B, Motor.C);
		} catch (Exception e ) { 
			// will not get thrown if we ensure that the args to the ctor are not null
		}
	}


	@Override
	public void driveRobotForward() {
		pmc.driveRobotForward();
	}

	@Override
	public void driveRobotBackward() {
		pmc.driveRobotBackward();
	}

	@Override
	public void turnRobotRight() {
		pmc.turnRobotRight();
	}

	@Override
	public void turnRobotLeft() {
		pmc.turnRobotLeft();
	}

	@Override
	public void stopRobotMotion() {
		pmc.stopRobotMotion();
	}

	@Override
	public void setVelocity(int velocity) {
		pmc.setVelocity(velocity);
	}

	@Override
	public int getVelocity() {
		return pmc.getVelocity();
	}
}
