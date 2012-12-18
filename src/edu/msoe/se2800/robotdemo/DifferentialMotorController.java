package edu.msoe.se2800.robotdemo;

import java.util.Observable;



import lejos.nxt.Sound;
import lejos.nxt.TouchSensor;
import lejos.nxt.remote.RemoteMotor;

/**
 * This class controls the synchronous operation of the motors that propel the
 * robot. Through differential drive, it operates the motors in a coordinated fashion 
 * in order to run the robot forward or backward in a straight line, and to
 * make the robot rotate right or left about its own vertical axis.
 * 
 * @author M. Hornick, based on a class originally written by Dr. Walt Schilling
 */
public class DifferentialMotorController implements iRobotMotionBehavior {
	// default velocity in inches/minute
	private static final int DEFAULT_VELOCITY = 500; 
	// The diameter of the tires in inches (2.125 inch for the smaller tires)
	private static final double WHEEL_DIAMETER = 2.125; 
	// The amount of rotation (in degrees) of the wheel per inch of wheel travel.
	private static final double DEGREES_PER_INCH = 360 / (WHEEL_DIAMETER * Math.PI); 
	private static final double SECONDS_PER_MINUTE = 60;

	private RemoteMotor leftMotor;	// the motor driving the left wheel of the robot 
	private RemoteMotor rightMotor; // the motor driving the right wheel of the robot
	private int velocity;			// speed of the robot in units of inches/minute

	/**
	 * This method creates a new instance of the DifferentialMotorController
	 * 
	 * @param lm reference to the RemoteMotor object that controls the motor connected to the wheel on the left side of the vehicle.
	 * @param rm reference to the RemoteMotor object that controls the motor connected to the wheel on the right side of the vehicle.
	 */
	public DifferentialMotorController(RemoteMotor lm, RemoteMotor rm) throws Exception {
		if( lm==null || rm==null )
			throw new Exception( "DifferentialMotorController: one or more constructor RemoteMotor reference arguments were null.");
		
		this.leftMotor = lm;
		this.rightMotor = rm;
		velocity = DEFAULT_VELOCITY;

		lm.resetTachoCount();	// set internal tachometers counts to 0
		rm.resetTachoCount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.msoe.se2800.robotdemo.iRobotMotionBehavior#driveRobotForward()
	 */
	@Override
	public void driveRobotForward() {
		// convert robot velocity (in/min) to motor speed (degrees/s)
		int motorSpeed = (int)( velocity /*INCHES_PER_MINUTE*/ * DEGREES_PER_INCH / SECONDS_PER_MINUTE );

		leftMotor.setSpeed(motorSpeed);
		rightMotor.setSpeed(motorSpeed);

		leftMotor.forward();
		rightMotor.forward();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.msoe.se2800.robotdemo.iRobotMotionBehavior#driveRobotBackward()
	 */
	@Override
	public void driveRobotBackward() {
		// convert robot velocity (in/min) to motor speed (degrees/s)
		int motorSpeed = (int)( velocity /*INCHES_PER_MINUTE*/ * DEGREES_PER_INCH / SECONDS_PER_MINUTE );

		leftMotor.setSpeed(motorSpeed);
		rightMotor.setSpeed(motorSpeed);

		leftMotor.backward();
		rightMotor.backward();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.msoe.se2800.robotdemo.iRobotMotionBehavior#turnRobotRight()
	 */
	@Override
	public void turnRobotRight() {
		// convert robot velocity (in/min) to motor speed (degrees/s)
		int motorSpeed = (int)( velocity /*INCHES_PER_MINUTE*/ * DEGREES_PER_INCH / SECONDS_PER_MINUTE );

		// Works by driving the left motor forward and the right motor backward.
		leftMotor.setSpeed(motorSpeed / 2);
		rightMotor.setSpeed(motorSpeed / 2);

		leftMotor.forward();
		rightMotor.backward();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.msoe.se2800.robotdemo.iRobotMotionBehavior#turnRobotLeft()
	 */
	@Override
	public void turnRobotLeft() {
		// convert robot velocity (in/min) to motor speed (degrees/s)
		int motorSpeed = (int)( velocity /*INCHES_PER_MINUTE*/ * DEGREES_PER_INCH / SECONDS_PER_MINUTE );

		// Works by driving the right motor forward and the left motor backward.
		leftMotor.setSpeed(motorSpeed / 2);
		rightMotor.setSpeed(motorSpeed / 2);

		leftMotor.backward();
		rightMotor.forward();
	}

	/**
	 * This method stops the motors from running.
	 */
	public void stopRobotMotion() {
		leftMotor.stop();
		rightMotor.stop();
	}

	/* (non-Javadoc)
	 * @see edu.msoe.se2800.robotdemo.iRobotMotionBehavior#getVelocity()
	 */
	@Override
	public int getVelocity() {
		return velocity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.msoe.se2800.robotdemo.iRobotMotionBehavior#setVelocity(int)
	 */
	@Override
	public void setVelocity(int velocity) {
		// Update the motor values and then store.
		if( velocity > 0 )  {// prevent invalid values
			int motorSpeed = (int)( velocity /*INCHES_PER_MINUTE*/ * DEGREES_PER_INCH / SECONDS_PER_MINUTE );
			leftMotor.setSpeed( motorSpeed );
			rightMotor.setSpeed( motorSpeed );

			this.velocity = velocity;
		} else { // invalid velocity specified
			Sound.beepSequenceUp(); // warning beep	for invalid velocity 		
		}
	}

}
