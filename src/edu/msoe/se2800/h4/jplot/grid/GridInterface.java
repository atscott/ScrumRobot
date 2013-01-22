package edu.msoe.se2800.h4.jplot.grid;

import java.awt.Component;

public interface GridInterface {
	
	public void initSubviews();
	
	public void addSubview(Component c, Object constraints);
	
	public Component getComponent();
	
	public void redraw();

}
