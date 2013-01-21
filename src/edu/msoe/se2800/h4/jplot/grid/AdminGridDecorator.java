package edu.msoe.se2800.h4.jplot.grid;

import edu.msoe.se2800.h4.jplot.JPoint;

public class AdminGridDecorator extends GridDecorator {

	public AdminGridDecorator(GridInterface grid) {
		super(grid);
	}
	
	@Override
	public void initSubviews() {
		super.initSubviews();
	}
	
	@Override
	public void addPoint(JPoint point) {
		grid.addPoint(point);
	}

}
