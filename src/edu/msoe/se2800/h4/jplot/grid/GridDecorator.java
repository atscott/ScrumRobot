package edu.msoe.se2800.h4.jplot.grid;

import java.awt.BorderLayout;
import java.awt.Component;

import edu.msoe.se2800.h4.jplot.InfoPanel;
import edu.msoe.se2800.h4.jplot.StatsPanel;

public abstract class GridDecorator implements GridInterface {
	
	protected GridInterface grid;
	protected InfoPanel infoPanel;
	protected StatsPanel statsPanel;
	
	public GridDecorator(GridInterface grid) {
		this.grid = grid;
	}
	
	public void initSubviews() {
		grid.initSubviews();
		infoPanel = new InfoPanel();
		infoPanel.initSubviews();
		addSubview(infoPanel, BorderLayout.EAST);
		statsPanel = new StatsPanel();
		statsPanel.initSubviews();
		addSubview(statsPanel, BorderLayout.NORTH);
	}
	
	@Override
	public void addSubview(Component c, Object constraints) {
		grid.addSubview(c, constraints);
	}

	@Override
	public Component getComponent() {
		return grid.getComponent();
	}

    @Override
    public void redraw() {
        grid.redraw();
    }
}
