package edu.msoe.se2800.h4.jplot;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class JPlot extends JFrame {
	
	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -8344597455042452839L;
	
	private Grid grid;
	
	public JPlot() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(true);
        setTitle("JPlot");
        getContentPane().setPreferredSize(new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
        
        grid = Grid.getInstance();
        grid.initSubviews();
        
		getContentPane().add(grid);
		
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
