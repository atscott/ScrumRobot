
package edu.msoe.se2800.h4.jplot.plotPanel;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import edu.msoe.se2800.h4.jplot.JPlotController;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import lejos.robotics.navigation.Waypoint;

@Test(groups={"requiresSequential"})
public class PlotPanelTest {

    private PlotPanel mPlot;
    private JPlotController mController;

    @BeforeClass
    public void beforeClass() {
        mPlot = new PlotPanel();
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

    @Test(description = "Tests the float method for accuracy on a variety of floats.", dataProvider = "roundData")
    public void testRound(Float valueToRound, Integer stepSize, Float delta, Float expected) {

        // TODO test for alternate step sizes. Right now, assume 10 is constant
        float result = mPlot.round(valueToRound);
        assertEquals(result, expected, delta);
    }
    
    @Test(enabled=false)
    public void testTranslateToLocation() {
        fail("unimplimented. Not sure what this method should do.");
    }
    
    @Test(enabled=false)
    public void testTranslateToNearestPoint() {
        fail("unimplimented. Not sure what this method should do.");
    }
    
    @Test(enabled=false)
    public void testGetInterceptedPoint() {
        fail("unimplimented. Not sure what this method should do.");
    }

    @DataProvider
    private Object[][] roundData() {
        return new Object[][] {
                {
                        Float.valueOf(0), 10, Float.valueOf(0), Float.valueOf(0)
                }, {
                        Float.valueOf(1), 10, Float.valueOf(0), Float.valueOf(0)
                }, {
                        Float.valueOf(-1), 10, Float.valueOf(0), Float.valueOf(0)
                }, {
                        Float.valueOf((float) 0.0), 10, Float.valueOf(0), Float.valueOf(0)
                }, {
                        Float.valueOf((float) 1.0), 10, Float.valueOf(0), Float.valueOf(0)
                }, {
                        Float.valueOf((float) -1.0), 10, Float.valueOf(0), Float.valueOf(0)
                }, {
                        Float.valueOf((float) .1), 10, Float.valueOf(0), Float.valueOf(0)
                }, {
                        Float.valueOf((float) -.1), 10, Float.valueOf(0), Float.valueOf(0)
                }, {
                        Float.valueOf((float) 1.1), 10, Float.valueOf(0), Float.valueOf(0)
                }, {
                        Float.valueOf((float) -1.1), 10, Float.valueOf(0), Float.valueOf(0)
                }, {
                        Float.valueOf((float) 5.0), 10, Float.valueOf(0), Float.valueOf(0)
                }, {
                        Float.valueOf((float) -5.0), 10, Float.valueOf(0), Float.valueOf(-10)
                }, {
                        Float.valueOf((float) .5), 10, Float.valueOf(0), Float.valueOf(0)
                }, {
                        Float.valueOf((float) -.5), 10, Float.valueOf(0), Float.valueOf(0)
                }
        };
    }

}
