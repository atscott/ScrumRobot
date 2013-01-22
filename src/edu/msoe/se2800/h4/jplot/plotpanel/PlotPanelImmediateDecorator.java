package edu.msoe.se2800.h4.jplot.plotpanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import edu.msoe.se2800.h4.jplot.JPlotController;
import edu.msoe.se2800.h4.jplot.JPoint;

public class PlotPanelImmediateDecorator extends PlotPanelDecorator {

	public PlotPanelImmediateDecorator(PlotPanelInterface plotPanel) {
		super(plotPanel);
		getComponent().addMouseListener(new ImmediateListener());
	}
	
	public void replacePoint(JPoint point) {
		JPlotController.getInstance().getPathPoints().clear();
		JPlotController.getInstance().addPoint(point);
		JPlotController.getInstance().getGrid().redraw();
	}

	public class ImmediateListener extends MouseAdapter {
		
		@Override
		public void mouseClicked(MouseEvent event) {
			JPoint p =  new JPoint(event.getX(), event.getY());
			replacePoint(translateToNearestPoint(p));
		}
		
	}
}
