package edu.msoe.se2800.h4;

import lejos.nxt.Motor;
import lejos.nxt.Sound;
import lejos.nxt.remote.RemoteMotor;

public class RobotController {
		/**
		 * default velocity in inches/minute
		 */
		private static final int DEFAULT_VELOCITY = 500; 
		// The diameter of the tires in inches (2.125 inch for the smaller tires)
		private static final double WHEEL_DIAMETER = 2.125; 
		
		private static final double DEGREES_PER_INCH = 360 / (WHEEL_DIAMETER * Math.PI); 
		
		private static final double SECONDS_PER_MINUTE = 60;
		/**
		 * // the motor driving the left wheel of the robot 
		 */
		private static final RemoteMotor LEFTMOTOR = Motor.B;	
		/**
		 * // the motor driving the right wheel of the robot
		 */
		private static final RemoteMotor RIGHTMOTOR = Motor.C; 
		/**
		 * speed of the robot (inches/minute)
		 */
		private int velocity = 0;
		
		private int motorSpeed = 0;
		
		
		public void driveForward(){
			
			LEFTMOTOR.setSpeed(motorSpeed);
			RIGHTMOTOR.setSpeed(motorSpeed);

			LEFTMOTOR.forward();
			RIGHTMOTOR.forward();
		}
		
		public void setVelocity(int velocity) {
			
			if( velocity > 0 )  {
				int motorSpeed = (int)( velocity * DEGREES_PER_INCH / SECONDS_PER_MINUTE );
				LEFTMOTOR.setSpeed( motorSpeed );
				RIGHTMOTOR.setSpeed( motorSpeed );

				this.velocity = velocity;
			} else { 
					
			}
		}
		
		public void stopRobotMotion() {
			LEFTMOTOR.stop();
			RIGHTMOTOR.stop();
		}
		public void driveRobotBackward() {
			int motorSpeed = (int)( velocity /*INCHES_PER_MINUTE*/ * DEGREES_PER_INCH / SECONDS_PER_MINUTE );

			LEFTMOTOR.setSpeed(motorSpeed);
			RIGHTMOTOR.setSpeed(motorSpeed);

			LEFTMOTOR.backward();
			RIGHTMOTOR.backward();
		}
}
