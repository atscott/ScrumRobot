package edu.msoe.se2800.h4.jplot.plotpanel;

import java.awt.Component;

import lejos.robotics.navigation.Waypoint;

public interface PlotPanelInterface {
	
	public Component getComponent();
	
	public Waypoint translateToNearestPoint(Waypoint p);
	
	public Waypoint getInterceptedPoint(Waypoint point);
	
	public Waypoint getActivePoint();
	
	public int getActivePointIndexHolder();
	
	public void setActivePoint(Waypoint p);
	
	public void setActivePointIndexHolder(int index);

}
