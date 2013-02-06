package edu.msoe.se2800.h4.jplot;

import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.testng.testcase.FestSwingTestngTestCase;
import org.testng.annotations.Test;

import edu.msoe.se2800.h4.administrationFeatures.DatabaseConnection;
import edu.msoe.se2800.h4.jplot.grid.Grid;

public class JPlotObserverTest extends FestSwingTestngTestCase {
	
	private FrameFixture mWindow;

    @Override
    protected void onSetUp() {
    	JPlot frame = GuiActionRunner.execute(new GuiQuery<JPlot>() {
            protected JPlot executeInEDT() {
              return new JPlot(DatabaseConnection.UserTypes.OBSERVER, new Grid());  
            }
        });
        // IMPORTANT: note the call to 'robot()'
        // we must use the Robot from FestSwingTestngTestCase        
        mWindow = new FrameFixture(robot(), frame);
        mWindow.show(); // shows the frame to test
    }
    
    @Test
    public void oMenuItemLogoutShouldBeAvailable() {
        mWindow.menuItem("logout").requireVisible().requireEnabled();
    }
    
    @Test
    public void oMenuItemImmediateModeShouldNotBeAvailable() {
        mWindow.menuItem("immediate_mode").requireNotVisible();
    }
    
    @Test
    public void oMenuItemAdministratorModeShouldNotBeAvailable() {
        mWindow.menuItem("administrator_mode").requireNotVisible();
    }
    
    @Test
    public void oMenuItemCreateUserShouldNotBeAvailable() {
        mWindow.menuItem("create_user").requireNotVisible();
    }
    
    @Test
    public void oMenuItemListUserShouldNotBeAvailable() {
        mWindow.menuItem("list_user").requireNotVisible();
    }

}
