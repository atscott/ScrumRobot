package edu.msoe.se2800.h4.jplot;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import edu.msoe.se2800.h4.AdministrationFeatures.DatabaseConnection;
import edu.msoe.se2800.h4.Path;
import edu.msoe.se2800.h4.jplot.Constants.GridMode;
import edu.msoe.se2800.h4.jplot.grid.Grid;
import edu.msoe.se2800.h4.jplot.grid.GridInterface;

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
			path.getPoints().clear();
			for (JPoint p : oldList) {
				path.add(p);
			}
		}
		Constants.CURRENT_MODE = mode;
		if (mode == GridMode.IMMEDIATE_MODE) {
			copyPoints();
			path.getPoints().clear();
		}
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				jplot.dispose();
				jplot = new JPlot(Constants.CURRENT_MODE, grid);
			}
		});
	}
	
    public void changeMode(DatabaseConnection.UserTypes accessLevel) {
        if (accessLevel == DatabaseConnection.UserTypes.ADMIN || accessLevel == DatabaseConnection.UserTypes.PROGRAMMER) {
            changeMode(GridMode.ADMINISTRATOR_MODE);
        } else {
            changeMode(GridMode.OBSERVER_MODE);
        }
    }

	public Path getPath() {
		return path;
	}
	
	public List<JPoint> getPathPoints() {
		return path.getPoints();
	}
	
	public void addPoint(JPoint point) {
		path.add(point);
		if (jplot != null) {
			jplot.repaint();
		}
	}
	
	public void removePoint(int indexOfPoint) {
		path.getPoints().remove(indexOfPoint);
		jplot.repaint();
	}
	
	public void copyPoints() {
		oldList.clear();
		for (JPoint j : getPathPoints()) {
			oldList.add(j);
		}
		getPathPoints().clear();
		grid.redraw();
	}
	
	public void zoomIn() {
		setGridDensity(getGridDensity() - 1);
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
			this.highlightedPoint = JPlotController.getInstance().getPath().get(indexInPointsArray);
		}
	}
}
