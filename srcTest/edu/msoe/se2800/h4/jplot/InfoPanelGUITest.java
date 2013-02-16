package edu.msoe.se2800.h4.jplot;

import dagger.Module;
import dagger.ObjectGraph;
import edu.msoe.se2800.h4.H4Module;
import edu.msoe.se2800.h4.IRobotController;
import edu.msoe.se2800.h4.administrationFeatures.DatabaseConnection;
import edu.msoe.se2800.h4.jplot.grid.Grid;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.testng.testcase.FestSwingTestngTestCase;
import org.testng.annotations.Test;

import javax.inject.Inject;
/**
 * Tests to verify that the Infopanel gets populated & enabled correctly.
 *
 * @author Kevin Tohtz, Marius Volkhart
 */
public class InfoPanelGUITest extends FestSwingTestngTestCase {

    private FrameFixture mWindow;
    /**
     * Robot Dependencies injected for automated tests
     */
    @Inject
    IRobotController robotController;

    @Module(
            entryPoints = InfoPanelGUITest.class,
            includes = H4Module.class
    )

    static class TestModule {
    }

    /**
     * Creates the GUI frame and Infopanel with Administrator Access
     */
    @Override
    protected void onSetUp() {
        ObjectGraph.create(new TestModule()).inject(this);
        JPlotController.getInstance().robotController = this.robotController;

        JPlot frame = GuiActionRunner.execute(new GuiQuery<JPlot>() {
            protected JPlot executeInEDT() {
                return new JPlot(DatabaseConnection.UserTypes.ADMIN, new Grid());
            }
        });

        // IMPORTANT: note the call to 'robot()'
        // we must use the Robot from FestSwingTestngTestCase
        mWindow = new FrameFixture(robot(), frame);
        mWindow.show(); // shows the frame to test
    }

    /**
     * Tests the Zoom + button availability
     */
    @Test
    public void zoomInButtonShouldBeAvailable() {
        mWindow.button("zoom_in").requireVisible().requireEnabled().requireText("Zoom +");
    }

    /**
     * Tests the Zoom - button availability
     */
    @Test
    public void zoomOutButtonShouldBeAvailable() {
        mWindow.button("zoom_out").requireVisible().requireEnabled().requireText("Zoom -");
    }

    /**
     * Tests the load button availability
     */
    @Test
    public void loadButtonShouldBeAvailable() {
        mWindow.button("load").requireVisible().requireEnabled().requireText("Load");
    }

    /**
     * Tests the save button availability
     */
    @Test
    public void saveButtonShouldBeAvailable() {
        mWindow.button("save").requireVisible().requireEnabled().requireText("Save");
    }

    /**
     * Tests that the save as button is available
     */
    @Test
    public void saveAsButtonShouldBeAvailable() {
        mWindow.button("save_as").requireVisible().requireEnabled().requireText("Save as...");
    }

    /**
     * Tests the visibility of the number of points label
     */
    @Test
    public void numberOfPointsLabelShouldBeVisible() {
        mWindow.label("number_of_points").requireVisible();
    }

    /**
     * Tests the visibility of the xy label
     */
    @Test
    public void xYLabelShouldBeVisible() {
        mWindow.label("xy").requireVisible();
    }

    /**
     * Tests the availability and visibility of the list x text field
     */
    @Test
    public void xFieldShouldBeAvailable() {
        mWindow.textBox("x_textfield").requireVisible().requireEnabled();
    }

    /**
     * Tests the availability and visibility of the list y text field
     */
    @Test
    public void yFieldShouldBeAvailable() {
        mWindow.textBox("y_textfield").requireVisible().requireEnabled();
    }

    /**
     * Tests the availability and visibility of the list of points text area
     */
    @Test
    public void pointsListShouldBeAvailable() {
        mWindow.list("points_list").requireVisible().requireEnabled();
    }

    /**
     * Tests the Forward button availability
     */
    @Test
    public void forwardButtonShouldBeAvailable() {
        mWindow.toggleButton("Forward").requireVisible().requireEnabled();
    }

    /**
     * Tests the Reverse button availability
     */
    @Test
    public void reverseButtonShouldBeAvailable() {
        mWindow.toggleButton("Reverse").requireVisible().requireEnabled();
    }

    /**
     * Tests the Go button availability
     */
    @Test
    public void goButtonShouldBeAvailable() {
        mWindow.button("Go").requireVisible().requireEnabled();
    }

    /**
     * Tests the Stop button availability
     */
    @Test
    public void stopButtonShouldBeAvailable() {
        mWindow.button("Stop").requireVisible().requireEnabled();
    }

    /**
     * Tests the Stop Now button availability
     */
    @Test
    public void stopNowButtonShouldBeAvailable() {
        mWindow.button("Stop Now").requireVisible().requireEnabled();
    }

    /**
     * Tests the Single Step button availability
     */
    @Test
    public void singleStepShouldBeAvailable() {
        mWindow.checkBox("Single Step").requireVisible().requireEnabled();
    }
}
