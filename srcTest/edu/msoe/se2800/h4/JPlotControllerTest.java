package edu.msoe.se2800.h4;

import org.testng.annotations.BeforeClass;

import edu.msoe.se2800.h4.jplot.JPlotController;

public class JPlotControllerTest {
	
	private JPlotController controller = JPlotController.getInstance();
	
	@BeforeClass
    public void setupGridTesting() {

        // Ensure that we dont get null pointer exceptions from JPlotController child views not being initialized
        controller.init();
    }

}
