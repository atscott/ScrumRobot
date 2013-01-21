package edu.msoe.se2800.h4.jplot.grid;

import edu.msoe.se2800.h4.jplot.JPoint;


public class ImmediateGridDecorator extends GridDecorator {
	
	public ImmediateGridDecorator(GridInterface grid) {
		super(grid);
	}
	
	@Override
	public void initSubviews() {
		super.initSubviews();
		infoPanel.disableSubviews();
	}
	
	@Override
	public void addPoint(JPoint point) {
		Grid.getInstance().getPathPoints().clear();
		grid.addPoint(point);
	}

}
