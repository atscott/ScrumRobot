package edu.msoe.se2800.h4.jplot.grid;


public class AdminGridDecorator extends GridDecorator {

	public AdminGridDecorator(GridInterface grid) {
		super(grid);
	}

	@Override
	public void redraw() {
		grid.redraw();
	}

}
