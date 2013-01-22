package edu.msoe.se2800.h4.jplot.plotpanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;

import edu.msoe.se2800.h4.jplot.Constants;
import edu.msoe.se2800.h4.jplot.JPlotController;
import edu.msoe.se2800.h4.jplot.JPoint;

public class PlotPanel extends JPanel implements PlotPanelInterface {
	
	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = 2975690672805786359L;
	
	private int activePointIndexHolder;
	private JPoint activePoint;

	public PlotPanel() {
		setPreferredSize(new Dimension(Constants.GRID_WIDTH(), Constants.GRID_HEIGHT));
		setBackground(Color.BLACK);
		setVisible(true);
		//TODO addMouseMotionListener(new PlotMouseMotionListener());
		//TODO addMouseListener(new PlotMouseAdapter());
		this.addMouseWheelListener(new PlotMouseWheelListener());
	}
	
	@Override
	public Component getComponent() {
		return this;
	}
	
	/**
	 * This method returns one of the points in the Grid class that is within 10 pixels of the passed
	 * in point.  If the passed in point is not within the 10 pixel tolerance, this method returns null.
	 * @param point
	 * @return the actual point in the Grid class that is within tolerance of the parameter point, otherwise null
	 */
	@Override
	public JPoint getInterceptedPoint(JPoint point) {
		JPoint retvalPoint = null;
		for (JPoint p : JPlotController.getInstance().getPathPoints()) {
			JPoint temp = translateToLocation(p);
			temp.x-=Constants.POINT_RADIUS;
			temp.y+=Constants.POINT_RADIUS;
			if ((point.x < temp.x+10 && point.x > temp.x-10) && (point.y < temp.y+10 && point.y > temp.y-10)) {
				retvalPoint = p;
			}
		}
		return retvalPoint;
	}
	
	/**
	 * This method draws the dark gray grid lines based on the GRID_DENSITY specified in the constants file.
	 * @param density the number of lines you would like visible on the screen
	 * @param g graphics used to draw the line
	 */
	public void drawGridLines(int density, Graphics g) {
		int drawingWidth = (Constants.GRID_WIDTH()/density)+Constants.GRID_WIDTH();
		int drawingHeight = (Constants.GRID_HEIGHT/density)+Constants.GRID_HEIGHT;
		
		g.setColor(Color.DARK_GRAY);
		
		/** Draws the vertical lines */
		for (int i=Constants.GRID_OFFSET; i<=drawingWidth;i+=(Constants.GRID_WIDTH()/density)) g.drawLine(i, 0, i, drawingHeight);
		
		/** Draws the horizontal lines */
		for (int i=(Constants.GRID_HEIGHT-Constants.GRID_OFFSET); i>=(Constants.GRID_HEIGHT-drawingHeight);i-=(Constants.GRID_HEIGHT/density)) g.drawLine(0, i, drawingWidth, i);
	}
	
	/**
	 * This method takes in a graphics instance that is used to draw a filled circle at the passed in point with a
	 * radius (POINT_RADIUS) specified in the constants file
	 * @param g
	 * @param p
	 */
	public void drawPoint(Graphics g, JPoint p) {
		if (JPlotController.getInstance().getHighlightedPoint() != null && JPlotController.getInstance().getHighlightedPoint().x == p.x && JPlotController.getInstance().getHighlightedPoint().y == p.y) {
			g.setColor(Color.ORANGE);
		}
		JPoint temp = translateToLocation(p);
		int x = temp.x;
		int y = temp.y;
		x-=Constants.POINT_RADIUS;
		y+=Constants.POINT_RADIUS;
		
		g.fillOval(x, y, Constants.POINT_RADIUS*2, Constants.POINT_RADIUS*2);
		
		drawCoordinatesAbovePoint(g, p, temp);
	}
	
	/**
	 * Draws the x and y values of the passed in coordinates Point above the location Point
	 * @param g graphics used to draw the x and y values
	 * @param coordinates the point holding the actual numbers that will be drawn on the screen
	 * @param location the point holding the location on screen that the coordinates will be drawn
	 */
	private void drawCoordinatesAbovePoint(Graphics g, JPoint coordinates, JPoint location) {
		if (Constants.HOVER_INDEX == JPlotController.getInstance().getPathPoints().indexOf(coordinates)) {
			g.setColor(Color.WHITE);
		} else {
			g.setColor(Color.LIGHT_GRAY);
		}
		g.drawString(""+coordinates.x+","+coordinates.y, location.x, location.y-10);
	}
	
	/**
	 * This method is usually called if the SNAP_TO_GRID_CORNERS is set to true in the Constants file and it takes a given
	 * integer and returns the value corresponding to the closest value of a "corner" on the grid.  It will be a multiple of
	 * STEP_INCREMENT in the constants file
	 * @param num, the number to round
	 * @return the rounded number
	 */
	public int round(int num) {
		int newNum;
		
		boolean keepStepping = true;
		int highestStep = 0;
		while (keepStepping) {
			if (highestStep >= num) {
				keepStepping = false;
			} else {
				highestStep+=Constants.STEP_INCREMENT;
			}
		}
		if ((highestStep-num) < (num-(highestStep-Constants.STEP_INCREMENT))) {
			newNum = highestStep;
		} else {
			newNum = (highestStep-Constants.STEP_INCREMENT);
		}
		return newNum;
	}
	
	public void drawLine(Graphics g, JPoint one, JPoint two) {
		JPoint translated1 = translateToLocation(one);
		JPoint translated2 = translateToLocation(two);
		
		//we must add 10 to the y coordinates because the translate method accounts for the offset for points, a line does not need this offset
		g.drawLine(translated1.x, translated1.y+10, translated2.x, translated2.y+10);
	}
	
	public JPoint translateToLocation(JPoint p) {
		JPoint translated = new JPoint();
		
		int x = p.x;
		int y = p.y;
		
		if (Constants.SNAP_TO_GRID_CORNERS) {
			x = round(x);
			y = round(y);
			
			//need this to update the points in the Grid class after rounding, a reference is passed in so we can just update 
			//the reference, which still resides in the Grid class so setting the x and y of the passed in point is ok
			p.x = x;
			p.y = y;
		}
		
		x = (int)Math.floor(0.5+((Constants.GRID_WIDTH()/JPlotController.getInstance().getGridDensity())*(x/Constants.STEP_INCREMENT)));
		y = (int)Math.floor(0.5+((Constants.GRID_HEIGHT/JPlotController.getInstance().getGridDensity())*(y/Constants.STEP_INCREMENT)));
		y = Constants.GRID_HEIGHT-Constants.GRID_OFFSET-y;
		x+=Constants.GRID_OFFSET;
		y-=Constants.GRID_OFFSET;
		translated.x = x;
		translated.y = y;
		
		return translated;
	}
	
	@Override
	public JPoint translateToNearestPoint(JPoint p) {
		JPoint translated = new JPoint();
		
		int x = p.x;
		int y = p.y;
		
		x-=Constants.GRID_OFFSET;
		y+=Constants.GRID_OFFSET;
		y = Constants.GRID_HEIGHT-Constants.GRID_OFFSET-y;
		x = (int)Math.floor(0.5+((x*Constants.STEP_INCREMENT)/(Constants.GRID_WIDTH()/JPlotController.getInstance().getGridDensity())));
		y = (int)Math.floor(0.5+((y*Constants.STEP_INCREMENT)/(Constants.GRID_HEIGHT/JPlotController.getInstance().getGridDensity())));
		
		
		if (Constants.SNAP_TO_GRID_CORNERS) {
			x = round(x);
			y = round(y);
		}
		
		translated.x = x;
		translated.y = y;
		
		return translated;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.BLACK);
		drawGridLines(JPlotController.getInstance().getGridDensity(), g);
		//loop that draws each point, synchronized helps to take care of accessing the same list
		//from all of these threads the GUI makes
		synchronized(JPlotController.getInstance().getPathPoints()) {
			for (JPoint p : JPlotController.getInstance().getPathPoints()) {
				g.setColor(Color.CYAN);
				if (JPlotController.getInstance().getPathPoints().indexOf(p) == Constants.DRAGGING_INDEX) {
					g.setColor(Color.ORANGE);
				}
				drawPoint(g, p);
			}
		}
		
		//loop that draws the lines between each point
		for (int i=0;i<JPlotController.getInstance().getPathPoints().size();i++) {
			if (i+1<JPlotController.getInstance().getPathPoints().size()) {
				g.setColor(Color.CYAN);
				if (Constants.DRAGGING_INDEX == i+1 || Constants.DRAGGING_INDEX == i) {
					g.setColor(Color.ORANGE);
				}
				drawLine(g, JPlotController.getInstance().getPathPoints().get(i), JPlotController.getInstance().getPathPoints().get(i+1));
			}
		}
	}
	
	private class PlotMouseWheelListener implements MouseWheelListener {

		@Override
		public void mouseWheelMoved(MouseWheelEvent event) {
			if (event.getWheelRotation() < 0) {
				JPlotController.getInstance().zoomIn();
			} else {
				JPlotController.getInstance().zoomOut();
			}
			
		}
		
	}
	
	/** Listeners and Adapters **/
	/*TODO private class PlotMouseAdapter extends MouseAdapter {
		
		@Override
		public void mouseClicked(MouseEvent event) {
			if (event.getButton() == MouseEvent.BUTTON1) {
				JPoint point = translateToNearestPoint(new JPoint(event.getX(), event.getY()));
				boolean found = false;
				for (JPoint p : JPlotController.getInstance().getGrid().getPathPoints()) {
					if (p.x == point.x && p.y == point.y) {
						found = true;
						JPlotController.getInstance().getGrid().setHighlightedPoint(JPlotController.getInstance().getGrid().getPathPoints().indexOf(p));
					}
				}
				if (found == false) {
					JPlotController.getInstance().getGrid().setHighlightedPoint(-5);
				}
				JPlotController.getInstance().getGrid().redraw();
			} else if (event.getButton() == MouseEvent.BUTTON3) {
				doPop(event);
			}
		}
		@Override
		public void mousePressed(MouseEvent event) {
			JPoint p = new JPoint(event.getX(), event.getY());
			activePoint = getInterceptedPoint(p);
			activePointIndexHolder = JPlotController.getInstance().getGrid().getPathPoints().indexOf(activePoint);
			Constants.DRAGGING_INDEX = activePointIndexHolder;
		}
		@Override
		public void mouseReleased(MouseEvent event) {
			activePoint = null;
			activePointIndexHolder = -5;
			Constants.DRAGGING_INDEX = -5;
			JPlotController.getInstance().getGrid().repaint();
			Constants.HOVER_INDEX = -5;
		}
	}*/
	
	/*TODO private class PlotMouseMotionListener implements MouseMotionListener {
		@Override
		public void mouseDragged(MouseEvent event) {
			if (activePoint != null) {
				activePoint = translateToNearestPoint(new JPoint(event.getX(),event.getY()));
				JPlotController.getInstance().getGrid().getPathPoints().set(activePointIndexHolder, activePoint);
				JPlotController.getInstance().getGrid().redraw();
			}
		}
		@Override
		public void mouseMoved(MouseEvent event) {
			if (activePoint == null) {
				JPoint p = getInterceptedPoint(new JPoint(event.getX(), event.getY()));
				if (p != null) {
					Constants.HOVER_INDEX = JPlotController.getInstance().getGrid().getPathPoints().indexOf(p);
				} else {
					Constants.HOVER_INDEX = -5;
				}
				JPlotController.getInstance().getGrid().repaint();
			}
		}
	}*/

	@Override
	public JPoint getActivePoint() {
		return activePoint;
	}

	@Override
	public int getActivePointIndexHolder() {
		return activePointIndexHolder;
	}
	
	@Override
	public void setActivePoint(JPoint p) {
		activePoint = p;
	}

	@Override
	public void setActivePointIndexHolder(int index) {
		activePointIndexHolder = index;
	}
	
}