package edu.msoe.se2800.h4.jplot;

import org.testng.annotations.Test;

import edu.msoe.se2800.h4.jplot.JPlotController;

/**
 * I found no need to test getters, so those are emitted.
 * @author Josh Ault
 */
public class JPlotControllerTest {
	
	private JPlotController controller = JPlotController.getInstance();
	
	@Test
	public void testInit() {
		
	}
	
	@Test
	public void testDatabaseAllowModeChange() {
		
	}
	
	@Test(dependsOnMethods = {"testDatabaseAllowModeChange"})
	public void changeModeTest() {
		
	}
	
	@Test
	public void addPointTest() {
		
	}
	
	@Test
	public void removePointTest() {
		
	}
	
	@Test
	public void copyPointsTest() {
		
	}
	
	@Test
	public void zoomInTest() {
		
	}
	
	@Test
	public void zoomOutTest() {
		
	}
	
	@Test
	public void setGridDensityTest() {
		
	}
	
	@Test
	public void setHighlightedPointTest() {
		
	}

}
