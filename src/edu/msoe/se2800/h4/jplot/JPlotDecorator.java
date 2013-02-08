package edu.msoe.se2800.h4.jplot;

import javax.swing.JFrame;


public abstract class JPlotDecorator implements JPlotInterface {
	
	protected JPlotInterface jplot;
	
	public JPlotDecorator(JPlotInterface jplot) {
		this.jplot = jplot;
	}
	
	@Override
	public JFrame getFrame() {
		return jplot.getFrame();
	}

}
