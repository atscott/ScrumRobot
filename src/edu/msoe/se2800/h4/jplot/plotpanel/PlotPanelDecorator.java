package edu.msoe.se2800.h4.jplot.plotpanel;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import edu.msoe.se2800.h4.jplot.Constants;
import edu.msoe.se2800.h4.jplot.JPlotController;
import edu.msoe.se2800.h4.jplot.JPoint;


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
				setActivePoint(translateToNearestPoint(new JPoint(event.getX(),event.getY())));
				JPlotController.getInstance().getPathPoints().set(getActivePointIndexHolder(), getActivePoint());
				JPlotController.getInstance().getGrid().redraw();
			}
		}
		@Override
		public void mouseMoved(MouseEvent event) {
			if (getActivePoint() == null) {
				JPoint p = getInterceptedPoint(new JPoint(event.getX(), event.getY()));
				if (p != null) {
					Constants.HOVER_INDEX = JPlotController.getInstance().getPathPoints().indexOf(p);
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
	public JPoint translateToNearestPoint(JPoint p) {
		return plotPanel.translateToNearestPoint(p);
	}
	
	@Override
	public JPoint getInterceptedPoint(JPoint point) {
		return plotPanel.getInterceptedPoint(point);
	}
	
	@Override
	public JPoint getActivePoint() {
		return plotPanel.getActivePoint();
	}
	
	@Override
	public int getActivePointIndexHolder() {
		return plotPanel.getActivePointIndexHolder();
	}
	
	@Override
	public void setActivePoint(JPoint p) {
		plotPanel.setActivePoint(p);
	}
	
	@Override
	public void setActivePointIndexHolder(int index) {
		plotPanel.setActivePointIndexHolder(index);
	}
}
