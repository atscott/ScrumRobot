package edu.msoe.se2800.h4.jplot.plotPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import lejos.robotics.navigation.Waypoint;
import edu.msoe.se2800.h4.jplot.JPlotController;

public class PlotPanelImmediateDecorator extends PlotPanelDecorator {

	public PlotPanelImmediateDecorator(PlotPanelInterface plotPanel) {
		super(plotPanel);
		getComponent().addMouseListener(new ImmediateListener());
	}
	
	public void replacePoint(Waypoint point) {
		JPlotController.getInstance().getPath().clear();
		JPlotController.getInstance().addPoint(point);
		JPlotController.getInstance().getGrid().redraw();
	}

	public class ImmediateListener extends MouseAdapter {
		
		@Override
		public void mouseClicked(MouseEvent event) {
			Waypoint p =  new Waypoint(event.getX(), event.getY());
			replacePoint(translateToNearestPoint(p));
		}
		
	}
}
