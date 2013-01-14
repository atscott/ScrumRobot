package edu.msoe.se2800.h4.jplot;

import edu.msoe.se2800.h4.Path;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

public class Grid extends JPanel {
	
	public static Grid getInstance() {
		return LazyHolder.instance;
	}
	
	private static class LazyHolder {
		private static Grid instance = new Grid();
	}
	
	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = 1623331249534914071L;
	
	private PlotPanel plotPanel;
	private AxisPanel xAxisPanel, yAxisPanel;
	private Path path;
	private InfoPanel infoPanel;
	private JPoint highlightedPoint;
	
	private int gridDensity = Constants.DEFAULT_GRID_DENSITY;

	private Grid() {
//        Collections.synchronizedList(new ArrayList<JPoint>());
		path = Path.INSTANCE;
		
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
		initSubviews();
		setVisible(true);
		
		redraw();
	}
	
	private void initSubviews() {
		plotPanel = new PlotPanel();
		xAxisPanel = new AxisPanel(Constants.HORIZONTAL);
		yAxisPanel = new AxisPanel(Constants.VERTICAL);
		infoPanel = new InfoPanel();
		
		add(xAxisPanel, BorderLayout.SOUTH);
		add(yAxisPanel, BorderLayout.WEST);
		add(infoPanel, BorderLayout.EAST);
		add(plotPanel, BorderLayout.CENTER);
	}
	
	public void addPoint(JPoint p) {
		path.add(p);
		infoPanel.setPointsLabel(path.getPoints().size());
		redraw();
	}
	
	public List<JPoint> getPathPoints() {
		return this.path.getPoints();
	}
	
	public void zoomIn() {
		setGridDensity(getGridDensity()-1);
		redraw();
	}
	
	public void zoomOut() {
		setGridDensity(getGridDensity()+1);
		redraw();
	}
	
	public void redraw() {
		repaint();
	}
	
	public int getGridDensity() {
		return gridDensity;
	}
	
	public void setGridDensity(int density) {
		if (density > 1) {
			gridDensity = density;
			redraw();
		}
	}
	
	public JPoint getHighlightedPoint() {
		return this.highlightedPoint;
	}
	
	public void setHighlightedPoint(int indexInPointsArray) {
		if (indexInPointsArray == -5) {
			this.highlightedPoint = null;
		} else {
			this.highlightedPoint = this.path.get(indexInPointsArray);
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.BLACK);
		g.clearRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
	}

}
