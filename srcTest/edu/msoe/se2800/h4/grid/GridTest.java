package edu.msoe.se2800.h4.grid;

import java.io.File;

import junit.framework.Assert;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import edu.msoe.se2800.h4.jplot.grid.Grid;

public class GridTest {
	
	private Grid g = new Grid();
	
	@BeforeClass
    public void setupGridTesting() {

        // Ensure that we dont get null pointer exceptions from Grid child views not being initialized
        g.initSubviews();
    }

	/**
	 * ensuring that adding a point adds the correct point
	 */
	/*@Test(dependsOnMethods = {"testNullPath"})
	public void testAddPoint() {
		g.addPoint(new JPoint(1,2));
		Assert.assertEquals(g.getPathPoints().size(), 1);
		Assert.assertEquals(g.getPathPoints().get(0).x, 1);
		Assert.assertEquals(g.getPathPoints().get(0).y, 2);
	}*///TODO mode to JPlotController test
	
	/**
	 * makes sure getting the list of points does not return null
	 */
	/*@Test
	public void testNullPointsList() {
		Assert.assertNotNull(g.getPathPoints());
	}*///TODO mode to JPlotController test
	
	/**
	 * makes sure the getters and setters for GridDensity are working
	 * and will not let the density go below 1
	 */
	/*@Test
	public void testGridDensity() {
		g.setGridDensity(5);
		Assert.assertEquals(g.getGridDensity(), 5);
		
		g.setGridDensity(0);
		Assert.assertEquals(g.getGridDensity(), 1);//Grid should not let density drop below 1
	}*///TODO mode to JPlotController test
	
	/**
	 * ensures that the zoom in method only zooms in by one step
	 * will require testGridDensity() to pass
	 */
	/*@Test(dependsOnMethods = { "testGridDensity" })
	public void testZoomIn() {
		g.setGridDensity(10);
		g.zoomIn();
		Assert.assertEquals(g.getGridDensity(), 9);
	}*///TODO mode to JPlotController test
	
	/**
	 * ensures that the zoom out method only zooms out by one step
	 * will require testGridDensity() to pass
	 */
	/*@Test(dependsOnMethods = { "testGridDensity" })
	public void testZoomOut() {
		g.setGridDensity(10);
		g.zoomOut();
		Assert.assertEquals(g.getGridDensity(), 11);
	}*///TODO mode to JPlotController test
	
	/**
	 * makes sure the getters and setters for loadedFile work
	 */
	@Test
	public void testLoadedFile() {
		File f = new File("file.txt");
		g.setLoadedPathFile(f);
		Assert.assertEquals(f, g.getLoadedPathFile());
	}
}
