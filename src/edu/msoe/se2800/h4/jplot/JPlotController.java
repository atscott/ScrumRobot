package edu.msoe.se2800.h4.jplot;

import edu.msoe.se2800.h4.jplot.Constants.GridMode;
import edu.msoe.se2800.h4.jplot.grid.Grid;
import edu.msoe.se2800.h4.jplot.grid.GridInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.SwingUtilities;

import lejos.robotics.navigation.Waypoint;
import lejos.robotics.pathfinding.Path;

public class JPlotController {

	private static JPlotController instance = null;
	
	private int gridDensity = Constants.DEFAULT_GRID_DENSITY;
	
	private JPlot jplot;
	private GridInterface grid;
	private Path path;
	private List<JPoint> oldList;
	private JPoint highlightedPoint;
	
	public static JPlotController getInstance() {
		if (instance == null) {
			synchronized(JPlotController.class) {
				if (instance == null) {
					instance = new JPlotController();
				}
			}
		}
		return instance;
	}
	
	private JPlotController() {
		path = new Path();
		oldList = new ArrayList<JPoint>();
		/*addPoint(new JPoint(10,20));
		addPoint(new JPoint(10,30));
		addPoint(new JPoint(10,40));
		addPoint(new JPoint(20,20));
		addPoint(new JPoint(40,30));
		addPoint(new JPoint(60,5));*/
		addPoint(new JPoint(0,0));
		addPoint(new JPoint(12,12));
		addPoint(new JPoint(24,24));
		addPoint(new JPoint(36,36));
		addPoint(new JPoint(48,48));
		addPoint(new JPoint(60,60));
	}
	
	public void init() {
		grid = new Grid();
		jplot = new JPlot(GridMode.OBSERVER_MODE, grid);
	}
	
	public GridInterface getGrid() {
		return grid;
	}
	
	public void changeMode(GridMode mode) {
		grid = new Grid();
		if (Constants.CURRENT_MODE == GridMode.IMMEDIATE_MODE) {
			path.clear();
			for (JPoint p : oldList) {
				path.add(p);
			}
		}
		Constants.CURRENT_MODE = mode;
		if (mode == GridMode.IMMEDIATE_MODE) {
			copyPoints();
			path.clear();
		}
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				jplot.dispose();
				jplot = new JPlot(Constants.CURRENT_MODE, grid);
			}
		});
	}
	
	public Path getPath() {
		return path;
	}
	
	public ListIterator<Waypoint> getPathPoints() {
	    return path.listIterator();
	}
	
	public void addPoint(JPoint point) {
		path.add(point);
		if (jplot != null) {
			jplot.repaint();
		}
	}
	
	public void removePoint(int indexOfPoint) {
		path.remove(indexOfPoint);
		jplot.repaint();
	}
	
	public void copyPoints() {
		oldList.clear();
		for (Waypoint j : path) {
			oldList.add((JPoint) j);
		}
		path.clear();
		grid.redraw();
	}
	
	public void zoomIn() {
		setGridDensity(getGridDensity()-1);
		grid.redraw();
	}
	
	public void zoomOut() {
		setGridDensity(getGridDensity()+1);
		grid.redraw();
	}
	
	public int getGridDensity() {
		return gridDensity;
	}
	
	public void setGridDensity(int density) {
		if (density > 1) {
			gridDensity = Math.min(density, 100);
			grid.redraw();
		} else {
			gridDensity = 1;
			grid.redraw();
		}
	}
	
	public JPoint getHighlightedPoint() {
		return this.highlightedPoint;
	}
	
	public void setHighlightedPoint(int indexInPointsArray) {
		if (indexInPointsArray == -5) {
			this.highlightedPoint = null;
		} else {
			this.highlightedPoint = (JPoint) JPlotController.getInstance().getPath().get(indexInPointsArray);
		}
	}
}
