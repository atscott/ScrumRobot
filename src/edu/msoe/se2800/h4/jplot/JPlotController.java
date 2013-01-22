package edu.msoe.se2800.h4.jplot;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import lejos.robotics.navigation.Waypoint;
import lejos.robotics.pathfinding.Path;
import edu.msoe.se2800.h4.UserListUI;
import edu.msoe.se2800.h4.jplot.Constants.GridMode;
import edu.msoe.se2800.h4.jplot.grid.Grid;
import edu.msoe.se2800.h4.jplot.grid.GridInterface;

public class JPlotController {

	private static JPlotController instance = null;
	
	private int gridDensity = Constants.DEFAULT_GRID_DENSITY;
	
	private JPlot jplot;
	private GridInterface grid;
	private Path path;
	private List<Waypoint> oldList;
	private Waypoint highlightedPoint;
	
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
		oldList = new ArrayList<Waypoint>();
		/*addPoint(new Waypoint(10,20));
		addPoint(new Waypoint(10,30));
		addPoint(new Waypoint(10,40));
		addPoint(new Waypoint(20,20));
		addPoint(new Waypoint(40,30));
		addPoint(new Waypoint(60,5));*/
		addPoint(new Waypoint(0,0));
		addPoint(new Waypoint(12,12));
		addPoint(new Waypoint(24,24));
		addPoint(new Waypoint(36,36));
		addPoint(new Waypoint(48,48));
		addPoint(new Waypoint(60,60));
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
			for (Waypoint p : oldList) {
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
	
	public Waypoint[] getPathPoints() {
		Waypoint[] points = new Waypoint[path.size()];
	    path.toArray(points);
	    return points;
	}
	
	public void addPoint(Waypoint point) {
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
			oldList.add((Waypoint) j);
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
	
	public Waypoint getHighlightedPoint() {
		return this.highlightedPoint;
	}
	
	public void setHighlightedPoint(int indexInPointsArray) {
		if (indexInPointsArray == -5) {
			this.highlightedPoint = null;
		} else {
			this.highlightedPoint = (Waypoint) JPlotController.getInstance().getPath().get(indexInPointsArray);
		}
	}
	
	public void createUser() {
		JOptionPane.showMessageDialog(null, "Someone implement creating a user.  This is in the createUser() in JPlotController.java", "TEAM .SCRUMBOT", JOptionPane.ERROR_MESSAGE);
	}
	
	public void listUsers() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
	        UIManager.put("swing.boldMetal", Boolean.FALSE);
                UserListUI.createAndShowGUI();
            }
        });
	}
}
