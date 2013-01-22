package edu.msoe.se2800.h4.jplot;

import lejos.robotics.navigation.Waypoint;


public class JPoint extends Waypoint {
	
	public JPoint(double x, double y) {
		this((int)x, (int)y);
	}
	
	public JPoint(int x, int y) {
		super(x,y);
	}
	
	@Override
	public String toString() {
		return ""+this.x+", "+this.y;
	}

}
