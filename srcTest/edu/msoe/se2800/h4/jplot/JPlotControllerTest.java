
package edu.msoe.se2800.h4.jplot;

import edu.msoe.se2800.h4.administrationFeatures.DatabaseConnection;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import lejos.robotics.navigation.Waypoint;

/**
 * I found no need to test getters, so those are emitted.
 * 
 * @author Josh Ault
 */
@Test(groups = {
        "requiresSequential"
})
public class JPlotControllerTest {

    private JPlotController controller = JPlotController.getInstance();

    @BeforeClass
    public void setupController() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                controller.init();
            }
        });
    }

    @Test
    public void testInit() {
        Assert.assertNotNull(controller.getGrid());
        Assert.assertNotNull(controller.getPath());
    }

    /**
     * This test is testing the allowing a user to change modes, and also testing the actual
     * changing of what mode you are in because the allow change method simply calls the change mode
     * method if it is allowed.
     * 
     * @throws InvocationTargetException
     * @throws InterruptedException
     */
    @Test
    public void testDatabaseAllowModeChange() throws InterruptedException,
            InvocationTargetException {
        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                controller.changeMode(DatabaseConnection.UserTypes.OBSERVER);
            }
        });
        Assert.assertEquals(Constants.CURRENT_MODE, DatabaseConnection.UserTypes.OBSERVER);

        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                controller.changeMode(DatabaseConnection.UserTypes.ADMIN);
            }
        });

        Assert.assertEquals(Constants.CURRENT_MODE, DatabaseConnection.UserTypes.ADMIN);

        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                controller.changeMode(DatabaseConnection.UserTypes.PROGRAMMER);
            }
        });

        Assert.assertEquals(Constants.CURRENT_MODE, DatabaseConnection.UserTypes.PROGRAMMER);
    }

    @Test
    public void addPointTest() {
        Waypoint w = new Waypoint(150, 170);
        controller.addPoint(w);
        Assert.assertEquals(controller.getPathPoints()[controller.getPathPoints().length - 1], w);
    }

    @Test
    public void removePointTest() {
        Waypoint w = new Waypoint(200, 200);
        controller.addPoint(w);
        int length = controller.getPathPoints().length;
        controller.removePoint(length - 1);
        Assert.assertEquals(controller.getPathPoints().length, length - 1);
    }

    @Test
    public void setGridDensityTest() {
        controller.setGridDensity(50);
        Assert.assertEquals(controller.getGridDensity(), 50);

        controller.setGridDensity(-1);
        Assert.assertEquals(controller.getGridDensity(), 1);

        controller.setGridDensity(200);
        Assert.assertEquals(controller.getGridDensity(), 100);
    }

    @Test(dependsOnMethods = {
            "setGridDensityTest"
    })
    public void zoomInTest() {
        controller.setGridDensity(10);
        controller.zoomIn();
        Assert.assertEquals(controller.getGridDensity(), 9);
    }

    @Test(dependsOnMethods = {
            "setGridDensityTest"
    })
    public void zoomOutTest() {
        controller.setGridDensity(10);
        controller.zoomOut();
        Assert.assertEquals(controller.getGridDensity(), 11);
    }

    @Test
    public void setHighlightedPointTest() {
        Waypoint w = new Waypoint(0, 0);
        controller.addPoint(w);
        controller.setHighlightedPoint(0);
        Assert.assertNotNull(controller.getHighlightedPoint());
        Assert.assertEquals(controller.getHighlightedPoint(), w);

        controller.setHighlightedPoint(-5);
        Assert.assertNull(controller.getHighlightedPoint());
    }

}
