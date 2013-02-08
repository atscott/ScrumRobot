
package edu.msoe.se2800.h4.jplot.plotPanel;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import junit.framework.Assert;
import lejos.robotics.navigation.Waypoint;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import edu.msoe.se2800.h4.jplot.Constants;
import edu.msoe.se2800.h4.jplot.JPlotController;

@Test(groups = {
        "requiresSequential"
})
public class PlotPanelTest {

    private PlotPanel mPlot;
    private JPlotController mController;

    @BeforeClass
    public void beforeClass() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(new Runnable() {

            @Override
            public void run() {
                mPlot = new PlotPanel();
            }
        });
    }

    @BeforeMethod
    public void beforeMethod() {
        mController = JPlotController.getInstance();
        mController.addPoint(new Waypoint(0, 0));
        mController.addPoint(new Waypoint(10, 10));
        mController.addPoint(new Waypoint(20, 20));
        mController.addPoint(new Waypoint(30, 30));
        mController.addPoint(new Waypoint(40, 40));
        mController.addPoint(new Waypoint(50, 50));
        mController.addPoint(new Waypoint(60, 60));
        mController.addPoint(new Waypoint(70, 70));
    }

    @Test(enabled = false)
    public void testTranslateToLocation() {
    	float spaceBetweenGridLines = (Constants.GRID_WIDTH()-Constants.GRID_OFFSET)/(JPlotController.getInstance().getGridDensity()*1.0f);
    	float x = (float)Constants.GRID_OFFSET+Constants.Y_AXIS_WIDTH+(0.5f*JPlotController.getInstance().getGridDensity()*spaceBetweenGridLines);
    	float y = (float)(Constants.WINDOW_HEIGHT - (Constants.GRID_OFFSET+Constants.X_AXIS_HEIGHT+(0.5f*JPlotController.getInstance().getGridDensity()*spaceBetweenGridLines)));
    	Waypoint p = mPlot.translateToLocation(new Waypoint(0,0));
    	assertEquals(p.x, x);
    	assertEquals(p.y, y);
    }

    @Test(enabled = false)
    public void testTranslateToNearestPoint() {
        float spaceBetweenGridLines = (Constants.GRID_WIDTH()-Constants.GRID_OFFSET)/(JPlotController.getInstance().getGridDensity()*1.0f);
    	float x = (float)Constants.GRID_OFFSET+Constants.Y_AXIS_WIDTH+(0.5f*JPlotController.getInstance().getGridDensity()*spaceBetweenGridLines);
    	float y = (float)(Constants.WINDOW_HEIGHT - (Constants.GRID_OFFSET+Constants.X_AXIS_HEIGHT+(0.5f*JPlotController.getInstance().getGridDensity()*spaceBetweenGridLines)));
    	Waypoint p = mPlot.translateToNearestPoint(new Waypoint(x,y));
    	assertEquals(p.x, 0);
    	assertEquals(p.y, 0);
    }

    @Test(enabled = false)
    public void testGetInterceptedPoint() {
    	float spaceBetweenGridLines = (Constants.GRID_WIDTH()-Constants.GRID_OFFSET)/(JPlotController.getInstance().getGridDensity()*1.0f);
    	float x = (float)Constants.GRID_OFFSET+Constants.Y_AXIS_WIDTH+(0.5f*JPlotController.getInstance().getGridDensity()*spaceBetweenGridLines);
    	float y = (float)(Constants.WINDOW_HEIGHT - (Constants.GRID_OFFSET+Constants.X_AXIS_HEIGHT+(0.5f*JPlotController.getInstance().getGridDensity()*spaceBetweenGridLines)));
    	Waypoint p = mPlot.translateToNearestPoint(new Waypoint(x,y));
    	Assert.assertNotNull(p);
    	assertEquals(p.x, 0);
    	assertEquals(p.y, 0);
    }

}
