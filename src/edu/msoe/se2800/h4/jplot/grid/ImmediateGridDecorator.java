package edu.msoe.se2800.h4.jplot.grid;



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
	public void redraw() {
		grid.redraw();
	}

}
