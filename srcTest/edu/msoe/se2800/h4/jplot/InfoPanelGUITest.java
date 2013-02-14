package edu.msoe.se2800.h4.jplot;

import dagger.Module;
import dagger.ObjectGraph;
import edu.msoe.se2800.h4.H4Module;
import edu.msoe.se2800.h4.administrationFeatures.DatabaseConnection;
import edu.msoe.se2800.h4.jplot.grid.Grid;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.testng.testcase.FestSwingTestngTestCase;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class InfoPanelGUITest extends FestSwingTestngTestCase {
    
    private FrameFixture mWindow;

    @Module(
            entryPoints = InfoPanelGUITest.class,
            //includes = RobotControllerH4.class
            includes = H4Module.class
    )
    static class TestModule {
    }

    @BeforeClass
    protected void setup() {
        ObjectGraph.create(new TestModule()).inject(this);
    }

    @Override
        protected void onSetUp() {
            JPlot frame = GuiActionRunner.execute(new GuiQuery<JPlot>() {
                public JPlot executeInEDT() {
                    return new JPlot(DatabaseConnection.UserTypes.ADMIN, new Grid());
                }
            });
        // IMPORTANT: note the call to 'robot()'
        // we must use the Robot from FestSwingTestngTestCase
        mWindow = new FrameFixture(robot(), frame);
        mWindow.show(); // shows the frame to test
    }
    
    @Test
    public void zoomInButtonShouldBeAvailable() {
        mWindow.button("zoom_in").requireVisible().requireEnabled().requireText("Zoom +");
    }
    
    @Test
    public void zoomOutButtonShouldBeAvailable() {
        mWindow.button("zoom_out").requireVisible().requireEnabled().requireText("Zoom -");
    }
    
    @Test
    public void loadButtonShouldBeAvailable() {
        mWindow.button("load").requireVisible().requireEnabled().requireText("Load");
    }
    
    @Test
    public void saveButtonShouldBeAvailable() {
        mWindow.button("save").requireVisible().requireEnabled().requireText("Save");
    }
    
    @Test
    public void saveAsButtonShouldBeAvailable() {
        mWindow.button("save_as").requireVisible().requireEnabled().requireText("Save as...");
    }
    
    @Test
    public void numberOfPointsLabelShouldBeVisible() {
        mWindow.label("number_of_points").requireVisible();
    }
    
    @Test
    public void xYLabelShouldBeVisible() {
        mWindow.label("xy").requireVisible();
    }
    
    @Test
    public void xFieldShouldBeAvailable() {
        mWindow.textBox("x_textfield").requireVisible().requireEnabled();
    }
    
    @Test
    public void yFieldShouldBeAvailable() {
        mWindow.textBox("y_textfield").requireVisible().requireEnabled();
    }
    
    @Test
    public void pointsListShouldBeAvailable() {
        mWindow.list("points_list").requireVisible().requireEnabled();
    }

    @Test
    public void forwardButtonShouldBeAvailable(){
        mWindow.button("Forward").requireVisible().requireEnabled();
    }

    @Test
    public void reverseButtonShouldBeAvailable(){
        mWindow.button("Reverse").requireVisible().requireEnabled();
    }

        @Test
    public void goButtonShouldBeAvailable(){
        mWindow.button("Go").requireVisible().requireEnabled();
    }

    @Test
    public void stopButtonShouldBeAvailable(){
        mWindow.button("Stop").requireVisible().requireEnabled();
    }

    @Test
    public void stopNowButtonShouldBeAvailable(){
        mWindow.button("Stop Now").requireVisible().requireEnabled();
    }

    @Test
    public void singleStepShouldBeAvailable(){
        mWindow.checkBox("Single Step").requireVisible().requireEnabled();
    }
}
