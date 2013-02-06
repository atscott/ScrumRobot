
package edu.msoe.se2800.h4.jplot;

import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.testng.testcase.FestSwingTestngTestCase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import edu.msoe.se2800.h4.administrationFeatures.DatabaseConnection;
import edu.msoe.se2800.h4.jplot.grid.Grid;

/**
 * Checks the content of menus for different login types.
 * @author marius
 *
 */
public class MenuGUITest extends FestSwingTestngTestCase {
    
    private FrameFixture mWindow;
    private String[] mMenuPath;
    private DatabaseConnection.UserTypes mGridMode;
    
    @Factory(dataProvider = "menuOptionsProvider")
    public MenuGUITest(DatabaseConnection.UserTypes mode, String...menuPath) {
        mMenuPath = menuPath;
        mGridMode = mode;
    }

    @Override
    protected void onSetUp() {
        JPlot frame = GuiActionRunner.execute(new GuiQuery<JPlot>() {
            protected JPlot executeInEDT() {
              return new JPlot(mGridMode, new Grid());  
            }
        });
        // IMPORTANT: note the call to 'robot()'
        // we must use the Robot from FestSwingTestngTestCase
        mWindow = new FrameFixture(robot(), frame);
        mWindow.show(); // shows the frame to test
        
    }
    
    @Test(description = "Checks the the menuitem for which the path is provided is visible")
    public void menuShouldHaveOption() {
        mWindow.menuItemWithPath(mMenuPath).requireVisible();
    }
    
    @DataProvider
    public static Object[][] menuOptionsProvider() {
        return new Object[][] {
                {DatabaseConnection.UserTypes.ADMIN, new String[]{"Operating Mode", "Observer Mode"}},
                {DatabaseConnection.UserTypes.ADMIN, new String[]{"Operating Mode", "Immediate Mode"}},
                {DatabaseConnection.UserTypes.ADMIN, new String[]{"Operating Mode", "Administrator Mode"}},
                
                {DatabaseConnection.UserTypes.OBSERVER, new String[]{"Operating Mode", "Observer Mode"}},
                {DatabaseConnection.UserTypes.OBSERVER, new String[]{"Operating Mode", "Immediate Mode"}},
                {DatabaseConnection.UserTypes.OBSERVER, new String[]{"Operating Mode", "Administrator Mode"}},
                
                {DatabaseConnection.UserTypes.OTHER, new String[]{"Operating Mode", "Observer Mode"}},
                {DatabaseConnection.UserTypes.OTHER, new String[]{"Operating Mode", "Immediate Mode"}},
                {DatabaseConnection.UserTypes.OTHER, new String[]{"Operating Mode", "Administrator Mode"}},
        };
    }
    

}
