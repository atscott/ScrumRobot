package edu.msoe.se2800.h4.jplot.grid;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.File;

import javax.swing.JPanel;

import edu.msoe.se2800.h4.jplot.AxisPanel;
import edu.msoe.se2800.h4.jplot.Constants;
import edu.msoe.se2800.h4.jplot.Constants.GridMode;
import edu.msoe.se2800.h4.jplot.plotPanel.PlotPanel;
import edu.msoe.se2800.h4.jplot.plotPanel.PlotPanelAdminDecorator;
import edu.msoe.se2800.h4.jplot.plotPanel.PlotPanelImmediateDecorator;
import edu.msoe.se2800.h4.jplot.plotPanel.PlotPanelInterface;

public class Grid extends JPanel implements GridInterface {
	
	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = 1623331249534914071L;
	
	private PlotPanelInterface plotPanel;
	private AxisPanel xAxisPanel, yAxisPanel;
	private File loadedFile;

	public Grid() {
		
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
		setVisible(true);
		
		redraw();
	}
	
	@Override
	public void initSubviews() {
		plotPanel = new PlotPanel();
		if (Constants.CURRENT_MODE == GridMode.ADMINISTRATOR_MODE) {
			plotPanel = new PlotPanelAdminDecorator(plotPanel);
		} else if (Constants.CURRENT_MODE == GridMode.IMMEDIATE_MODE) {
			plotPanel = new PlotPanelImmediateDecorator(plotPanel);
		}
		xAxisPanel = new AxisPanel(Constants.HORIZONTAL);
		yAxisPanel = new AxisPanel(Constants.VERTICAL);
		
		add(xAxisPanel, BorderLayout.SOUTH);
		add(yAxisPanel, BorderLayout.WEST);
		add(plotPanel.getComponent(), BorderLayout.CENTER);
	}
	
	@Override
	public void addSubview(Component c, Object constraints) {
		add(c, constraints);
	}
	
	@Override
	public void redraw() {
		repaint();
	}
	
	public File getLoadedPathFile() {
		return loadedFile;
	}
	
	public void setLoadedPathFile(File file) {
		this.loadedFile = file;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.BLACK);
		g.clearRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
	}

	@Override
	public Component getComponent() {
		return this;
	}

}
