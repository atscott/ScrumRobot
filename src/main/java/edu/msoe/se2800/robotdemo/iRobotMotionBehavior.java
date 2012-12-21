package edu.msoe.se2800.robotdemo;

/**
 * This interface defines some simple primitive behaviors for a robot; specifically:
 * driving the robot forward or backward in a straight line, and 
 * rotating the robot right or left about its own vertical axis.
 * Note that additional behavioral methods would be needed (perhaps with arguments)
 * in order to get the robot to turn through an arc of radius R for X degrees.
 * 
 * @author M. Hornick, based on a class originally written by Dr. Walt Schilling
 * 
 */
public interface iRobotMotionBehavior {

	/**
	 * This method causes robot to move in a forward direction. 
	 * The speed is determined by the robot velocity setting.
	 */
	/*public abstract*/ void driveRobotForward();

	/**
	 * This method causes robot to move in a backward direction. 
	 * The speed is determined by the robot velocity setting.
	 */
	/*public abstract*/ void driveRobotBackward();

	/**
	 * This method causes the robot to rotate right (clockwise) about
	 * it's own vertical axis. The speed is determined by the motor
	 * velocity setting.
	 */
	/*public abstract*/ void turnRobotRight();

	/**
	 * This method causes the robot to rotate left (counter-clockwise) about
	 * it's own vertical axis. The speed is determined by the motor
	 * velocity setting.
	 */
	/*public abstract*/  void turnRobotLeft();
	
	/**
	 * This method stops the robot.
	 */
	/*public abstract*/ void stopRobotMotion();

	/**
	 * This method sets the velocity for the robot.
	 * 
	 * @param velocity the speed of the robot in inches per minute.
	 */
	/*public abstract*/ void setVelocity(int velocity);

	/**
	 * This method returns the currently-set robot velocity.
	 * 
	 * @return the velocity
	 */
	/*public abstract*/ int getVelocity();

}