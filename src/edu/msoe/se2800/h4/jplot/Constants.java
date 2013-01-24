package edu.msoe.se2800.h4.jplot;

public class Constants {
	
	public static enum GridMode {
		OBSERVER_MODE,
		IMMEDIATE_MODE,
		ADMINISTRATOR_MODE,
	}

    /** These are dynamic values.  If anything in this class depends on these values, a getter
	 * should be implemented for thos values.
	 */
	public static GridMode CURRENT_MODE = GridMode.OBSERVER_MODE;
	public static int INFO_PANEL_WIDTH = 0;//WINDOW_WIDTH-GRID_WIDTH-Y_AXIS_WIDTH;
	
	public static int DRAGGING_INDEX = -5;
	public static int HOVER_INDEX = -5;
	
	public static int ZOOM = 1;
	
	/**You may change these values**/
	public static final int WINDOW_HEIGHT = 600;
	public static final int WINDOW_WIDTH = 800;
	public static final int DEFAULT_GRID_DENSITY = 20;
	public static final int STEP_INCREMENT = 10;
	
	/** DO NOT CHANGE VALUES BELOW THIS LINE **/
	public static final int Y_AXIS_WIDTH = 50;
	public static final int X_AXIS_HEIGHT = 50;
	
	public static final int GRID_HEIGHT = WINDOW_HEIGHT-50;
	
	public static final int GRID_OFFSET = 10;
	
	public static final int VERTICAL = 1;
	public static final int HORIZONTAL = 2;
	
	public static final int POINT_RADIUS = 5;
	
	public static final boolean SNAP_TO_GRID_CORNERS = true;

    public static int GRID_WIDTH() {
        int GRID_WIDTH = WINDOW_WIDTH - INFO_PANEL_WIDTH - Y_AXIS_WIDTH;
		return GRID_WIDTH;
	}

}
