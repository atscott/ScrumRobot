package edu.msoe.se2800.h4.jplot.plotpanel;

import java.awt.Component;

import edu.msoe.se2800.h4.jplot.JPoint;

public interface PlotPanelInterface {
	
	public Component getComponent();
	
	public JPoint translateToNearestPoint(JPoint p);
	
	public JPoint getInterceptedPoint(JPoint point);
	
	public JPoint getActivePoint();
	
	public int getActivePointIndexHolder();
	
	public void setActivePoint(JPoint p);
	
	public void setActivePointIndexHolder(int index);

}
