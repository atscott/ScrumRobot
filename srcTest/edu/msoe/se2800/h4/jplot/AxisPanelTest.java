
package edu.msoe.se2800.h4.jplot;

import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import static org.testng.Assert.*;

/**
 * User: scottat Date: 1/24/13
 */
@Test(groups = {
        "requiresSequential"
})
public class AxisPanelTest {
    private final int TEST_DENSITY = 50;

    @SuppressWarnings("unused")
    @Test(description = "This is testing to see that and exception is thrown if illegal orientation is sent")
    public void illegalOrientationTest() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(new Runnable() {

            @Override
            public void run() {
                boolean caughtException = false;
                try {
                    new AxisPanel(4567);
                } catch (IllegalArgumentException e) {
                    caughtException = true;
                }

                if (!caughtException) {
                    fail("Failed to throw an IllegalArgumentException");
                }
            }
        });
    }

    @Test(description = "This is a test to make sure the panel can get initialized with both orientations")
    public void legalOrientationTest() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(new Runnable() {

            @Override
            public void run() {
                AxisPanel temp = new AxisPanel(Constants.HORIZONTAL);

                // if the horizontal is initiated, the panel should have the height of the x axis
                // and the
                // width of the entire grid
                // That is, the panel should span across the entire grid and the height should be
                // what is
                // predefined
                assertEquals((int) temp.getPreferredSize().getHeight(), Constants.X_AXIS_HEIGHT);
                assertEquals((int) temp.getPreferredSize().getWidth(), Constants.GRID_WIDTH());
                temp = new AxisPanel(Constants.VERTICAL);
                assertEquals((int) temp.getPreferredSize().getHeight(), Constants.GRID_HEIGHT);
                assertEquals((int) temp.getPreferredSize().getWidth(), Constants.Y_AXIS_WIDTH);
            }
        });

    }

    @Test(description = "This is a test to make sure the expected number of indicators are drawn based on TEST_DENSITY")
    public void drawTest() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(new Runnable() {

            @Override
            public void run() {
                CustomGraphics g = new CustomGraphics();
                AxisPanel temp = new AxisPanel(Constants.HORIZONTAL);
                temp.drawAxisMarkersHorizontal(TEST_DENSITY, g);
                // add one to the TEST_DENSITY because 0 is always drawn
                assertEquals(g.drawStringCalls.size(),TEST_DENSITY);
                //we must substract the STEP_INCREMENT value because it always draws the right/top side
                //as 10 less than the left/bottom side
                assertTrue(g.drawStringCalls.get(TEST_DENSITY-1)[0].equals(TEST_DENSITY/2
                        * Constants.STEP_INCREMENT - Constants.STEP_INCREMENT + ""));
            }
        });
    }

    @Test(description = "This is a test to make sure the expected number of indicators are drawn based on TEST_DENSITY")
    public void veritcalDrawTest() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(new Runnable() {

            @Override
            public void run() {
                CustomGraphics g = new CustomGraphics();
                AxisPanel temp = new AxisPanel(Constants.VERTICAL);
                temp.drawAxisMarkersHorizontal(TEST_DENSITY, g);
                assertEquals(g.drawStringCalls.size(),TEST_DENSITY);
                // verify that the last call was drawing the number TEST_DENSITY*step_increment
                //we must substract the STEP_INCREMENT value because it always draws the right/top side
                //as 10 less than the left/bottom side
                assertTrue(g.drawStringCalls.get(TEST_DENSITY-1)[0].equals(TEST_DENSITY/2
                        * Constants.STEP_INCREMENT - Constants.STEP_INCREMENT + ""));
            }
        });
    }

    @Test //TODO @Andrew put a description
    public void testNegativeDensity() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(new Runnable() {

            @Override
            public void run() {
                boolean caughtException = false;
                try {
                    CustomGraphics g = new CustomGraphics();
                    AxisPanel temp = new AxisPanel(Constants.VERTICAL);
                    temp.drawAxisMarkersHorizontal(-5, g);
                } catch (IllegalArgumentException e) {
                    caughtException = true;
                }

                if (!caughtException) {
                    fail("Failed to throw an IllegalArgumentException");
                }
            }
        });
    }

    private class CustomGraphics extends DebugGraphics {
        public ArrayList<String[]> drawStringCalls = new ArrayList<String[]>();

        @Override
        public void drawString(String s, int x, int y) {
            drawStringCalls.add(new String[] {
                    s, "" + x, "" + y
            });
        }

        @Override
        public void setColor(Color c) {
            // do nothing
        }
    }

}
