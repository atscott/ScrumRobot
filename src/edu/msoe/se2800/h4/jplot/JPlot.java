package edu.msoe.se2800.h4.jplot;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import edu.msoe.se2800.h4.jplot.Constants.GridMode;
import edu.msoe.se2800.h4.jplot.grid.AdminGridDecorator;
import edu.msoe.se2800.h4.jplot.grid.Grid;
import edu.msoe.se2800.h4.jplot.grid.GridInterface;
import edu.msoe.se2800.h4.jplot.grid.ImmediateGridDecorator;

public class JPlot extends JFrame {
	
	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -8344597455042452839L;
	
	private GridInterface grid;
	
	public JPlot() {
		this(GridMode.OBSERVER_MODE);
	}
	
	public JPlot(GridMode mode) {
		Constants.CURRENT_MODE = mode;
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("JPlot");
        getContentPane().setPreferredSize(new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
        
        grid = Grid.getInstance();
        if (mode != GridMode.OBSERVER_MODE) {
        	Constants.INFO_PANEL_WIDTH = 150;
	        if (mode == GridMode.ADMINISTRATOR_MODE) {
	        	grid = new AdminGridDecorator(grid);
			} else if (mode == GridMode.IMMEDIATE_MODE) {
	        	grid = new ImmediateGridDecorator(grid);
	        }
        }
        grid.initSubviews();
        
		getContentPane().add(grid.getComponent());
		
		pack();
		setVisible(true);
		
		/*grid.addPoint(new Point(10,20));
		grid.addPoint(new Point(10,30));
		grid.addPoint(new Point(10,40));
		grid.addPoint(new Point(20,20));
		grid.addPoint(new Point(40,30));
		grid.addPoint(new Point(60,5));*/
		grid.addPoint(new JPoint(0,0));
		grid.addPoint(new JPoint(12,12));
		grid.addPoint(new JPoint(24,24));
		grid.addPoint(new JPoint(36,36));
		grid.addPoint(new JPoint(48,48));
		grid.addPoint(new JPoint(60,60));
	}

}
