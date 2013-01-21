package edu.msoe.se2800.h4.jplot.grid;

import java.awt.Component;

import edu.msoe.se2800.h4.jplot.JPoint;

public interface GridInterface {
	
	public void initSubviews();
	
	public void addSubview(Component c, Object constraints);
	
	public Component getComponent();
	
	public void addPoint(JPoint point);

}
