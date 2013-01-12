package edu.msoe.se2800.h4.jplot;

import java.awt.Point;

public class JPoint extends Point {

	/** Generated serialVersionUID */
	private static final long serialVersionUID = 4437812717373387975L;
	
	public JPoint() {
		super();
	}
	
	public JPoint(int x, int y) {
		super(x,y);
	}
	
	@Override
	public String toString() {
		return ""+this.x+", "+this.y;
	}

}
