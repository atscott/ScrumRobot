package edu.msoe.se2800.h4.control;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Waypoint;
import lejos.robotics.pathfinding.Path;


public class Robot {
	double angle = 0;
	double length = 0;
	
	double x;
	double y;
	
	double zeroAngle = 0;
	
	
	private ArrayList<Double[]> go = new ArrayList<Double[]>(); // length, angle
	
	private DifferentialPilot pilot;
	
	private Path points;
	
	public Robot(){
		pilot = new DifferentialPilot(2, 6.85, Motor.B, Motor.C, false);
		points = new Path();
	}

	public void setPoints(Path path){
		points = path;
		for(Waypoint wp: points){
			x = wp.getX();
			y = wp.getY();
			length = Math.sqrt((y*y)+(x*x));
			calculateAngle();
			zeroAngle = -angle;
			Double[] coordinate = new Double[3];
			coordinate[0] = length;
			coordinate[1] = angle;
			coordinate[2] = zeroAngle;
			go.add(coordinate);
		}

		
	}
	
	public void calculateAngle(){
		System.out.println(x);
		System.out.println(y);
		angle = Math.tan(y/x);
		angle = (angle*360)/(2*Math.PI);
		if((x<0)&&(y<0)){
			angle+=180;
			System.out.println(angle);
		}
		else if(y>0&&x<0){
			angle+=180;
			System.out.println(angle);
		}
		
	}
	
	public void travelPath(){
		for(Double[] coordinate: go){
			pilot.rotate(coordinate[1]);
			pilot.travel(coordinate[0]);
			pilot.rotate(coordinate[2]);
			
			//pilot.quickStop();
			
			//while(pilot.isMoving())Thread.yield();

		}
	
	}
	
	
	
}
