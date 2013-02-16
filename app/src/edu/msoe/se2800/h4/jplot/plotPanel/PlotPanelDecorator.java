package edu.msoe.se2800.h4.jplot.plotPanel;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import lejos.robotics.navigation.Waypoint;
import edu.msoe.se2800.h4.jplot.Constants;
import edu.msoe.se2800.h4.jplot.JPlotController;


public abstract class PlotPanelDecorator implements PlotPanelInterface {
	
	protected PlotPanelInterface plotPanel;
	
	public PlotPanelDecorator(PlotPanelInterface plotPanel) {
		this.plotPanel = plotPanel;
		getComponent().addMouseMotionListener(new PlotMouseMotionListener());
	}
	
	private class PlotMouseMotionListener implements MouseMotionListener {
		@Override
		public void mouseDragged(MouseEvent event) {
			if (getActivePoint() != null) {
				setActivePoint(translateToNearestPoint(new Waypoint(event.getX(),event.getY())));
				JPlotController.getInstance().getPath().set(getActivePointIndexHolder(), getActivePoint());
				JPlotController.getInstance().getGrid().redraw();
			}
		}
		@Override
		public void mouseMoved(MouseEvent event) {
			if (getActivePoint() == null) {
				Waypoint p = getInterceptedPoint(new Waypoint(event.getX(), event.getY()));
				if (p != null) {
					Constants.HOVER_INDEX = JPlotController.getInstance().getPath().indexOf(p);
				} else {
					Constants.HOVER_INDEX = -5;
				}
				JPlotController.getInstance().getGrid().redraw();
			}
		}
	}
	
	@Override
	public Component getComponent() {
		return plotPanel.getComponent();
	}
	
	@Override
	public Waypoint translateToNearestPoint(Waypoint p) {
		return plotPanel.translateToNearestPoint(p);
	}
	
	@Override
	public Waypoint getInterceptedPoint(Waypoint point) {
		return plotPanel.getInterceptedPoint(point);
	}
	
	@Override
	public Waypoint getActivePoint() {
		return plotPanel.getActivePoint();
	}
	
	@Override
	public int getActivePointIndexHolder() {
		return plotPanel.getActivePointIndexHolder();
	}
	
	@Override
	public void setActivePoint(Waypoint p) {
		plotPanel.setActivePoint(p);
	}
	
	@Override
	public void setActivePointIndexHolder(int index) {
		plotPanel.setActivePointIndexHolder(index);
	}
}
