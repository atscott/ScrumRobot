package edu.msoe.se2800.h4.jplot;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

import edu.msoe.se2800.h4.jplot.Constants.GridMode;
import edu.msoe.se2800.h4.jplot.grid.AdminGridDecorator;
import edu.msoe.se2800.h4.jplot.grid.GridInterface;
import edu.msoe.se2800.h4.jplot.grid.ImmediateGridDecorator;

public class JPlot extends JFrame {
	
	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -8344597455042452839L;
	
	public JPlot(GridMode mode, GridInterface grid) {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("JPlot - "+mode);
        getContentPane().setPreferredSize(new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
        
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
		
		JMenuBar jMenuBar = new JMenuBar();
		JMenu jMenuMode = new JMenu();
		jMenuMode.setText("Operating Mode");
		
		JMenuItem mnuObserver = new JMenuItem();
		mnuObserver.setText("Observer Mode");
		mnuObserver.setActionCommand("observer");
		mnuObserver.addActionListener(new MenuActionListener());
		
        JMenuItem mnuImmediate = new JMenuItem();
        mnuImmediate.setText("Immediate Mode");
        mnuImmediate.setActionCommand("immediate");
        mnuImmediate.addActionListener(new MenuActionListener());
        
        JMenuItem mnuAdministrator = new JMenuItem();
		mnuAdministrator.setText("Administrator Mode");
		mnuAdministrator.setActionCommand("administrator");
		mnuAdministrator.addActionListener(new MenuActionListener());
		
        jMenuMode.add(mnuObserver);
        jMenuMode.add(mnuImmediate);
        jMenuMode.add(mnuAdministrator);
        
        jMenuBar.add(jMenuMode);
        setJMenuBar(jMenuBar);
        
		pack();
		setVisible(true);
	}
	
	public class MenuActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equalsIgnoreCase("observer")) {
				System.out.println("you chose observer mode");
				JPlotController.getInstance().changeMode(GridMode.OBSERVER_MODE);
			} else if (e.getActionCommand().equalsIgnoreCase("immediate")) {
				System.out.println("you chose immediate mode");
				JPlotController.getInstance().changeMode(GridMode.IMMEDIATE_MODE);
			} else if (e.getActionCommand().equalsIgnoreCase("administrator")) {
				System.out.println("you chose administrator mode");
				JPlotController.getInstance().changeMode(GridMode.ADMINISTRATOR_MODE);
			}
		}
		
	}

}
