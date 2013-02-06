package edu.msoe.se2800.h4.jplot;

import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.testng.testcase.FestSwingTestngTestCase;
import org.testng.annotations.Test;

import edu.msoe.se2800.h4.administrationFeatures.DatabaseConnection;
import edu.msoe.se2800.h4.jplot.grid.Grid;

public class JPlotTest extends FestSwingTestngTestCase {
	
	private FrameFixture mWindow;

	@Override
    protected void onSetUp() {
    	JPlotInterface frame = GuiActionRunner.execute(new GuiQuery<JPlotInterface>() {
            protected JPlotInterface executeInEDT() {
            	JPlotInterface jplotInterface= new JPlotAdminDecorator(new JPlotProgrammerDecorator(new JPlot(DatabaseConnection.UserTypes.ADMIN, new Grid())));
            	jplotInterface.initSubviews();
            	return jplotInterface;
            }
        });
        // IMPORTANT: note the call to 'robot()'
        // we must use the Robot from FestSwingTestngTestCase        
        mWindow = new FrameFixture(robot(), frame.getFrame());
        mWindow.show(); // shows the frame to test
    }
    
    @Test
    public void aMenuItemLogoutShouldBeAvailable() {
        mWindow.menuItem("logout").requireVisible().requireEnabled();
    }
    
    @Test
    public void aMenuItemImmediateModeShouldBeAvailable() {
        mWindow.menuItem("immediate_mode").requireVisible().requireEnabled();
    }
    
    @Test
    public void aMenuItemAdministratorModeShouldBeAvailable() {
        mWindow.menuItem("administrator_mode").requireVisible().requireEnabled();
    }
    
    @Test
    public void aMenuItemCreateUserShouldBeAvailable() {
        mWindow.menuItem("create_user").requireVisible().requireEnabled();
    }
    
    @Test
    public void aMenuItemListUserShouldBeAvailable() {
        mWindow.menuItem("list_user").requireVisible().requireEnabled();
    }
}
