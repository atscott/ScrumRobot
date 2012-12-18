package edu.msoe.se2800.robotdemo;

import lejos.nxt.Sound;

/**
 * @author hornick
 * This simple application demonstrates how to move the robot.
 *
 */
public class RobotDemoApp {

	/**
	 * @param args not used
	 */
	public static void main(String[] args) {
		Robot wallE = new Robot();
		Sound.beepSequenceUp();
		try {
			wallE.setVelocity(250);		// 250 in/s
			wallE.driveRobotForward();	// start moving forward
			Thread.sleep(4000);			// wait (robot continues to move)
			wallE.stopRobotMotion();	// stop robot
			
			wallE.setVelocity(400);		// 400 in/s
			wallE.turnRobotLeft();		// turn left
			Thread.sleep(3000);			// wait (robot continues to turn left)
			wallE.stopRobotMotion();	// stop robot
			
			Thread.sleep(1000);			// wait 2s (robot continues to pause)
			wallE.setVelocity(200);		// 200 in/s
			wallE.turnRobotRight();		// turn right
			Thread.sleep(6000);			// wait (robot continues to turn right)
			wallE.stopRobotMotion();	// stop robot
			
			wallE.setVelocity(100);		// 100 in/s
			wallE.driveRobotBackward();	// start moving backward
			Thread.sleep(3000);			// wait (robot continues to move)
			wallE.stopRobotMotion();	// stop robot
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			Sound.beep();
		}
		Sound.beepSequenceUp();
	}
}
