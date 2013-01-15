package edu.msoe.se2800.h4;

import junit.framework.Assert;

import org.testng.annotations.Test;

import edu.msoe.se2800.h4.jplot.Grid;
import edu.msoe.se2800.h4.jplot.JPoint;

public class GridTest {
	
	/**
	 * This test makes sure that after you initialize the Grid, the internal Path
	 * is not null
	 */
	@Test
	public void testNullPathConstructor() {
		Grid g = Grid.getInstance();
		Assert.assertNotNull(g.getPath());
	}

	/**
	 * ensuring that adding a point adds the correct point
	 */
	@Test
	public void testAddPoint() {
		Grid g = Grid.getInstance();
		g.addPoint(new JPoint(1,2));
		Assert.assertEquals(g.getPathPoints().size(), 1);
		Assert.assertEquals(g.getPathPoints().get(0).x, 1);
		Assert.assertEquals(g.getPathPoints().get(0).y, 2);
	}
	
	/**
	 * makes sure getting the list of points does not return null
	 */
	@Test
	public void testNullPointsList() {
		Assert.assertNotNull(Grid.getInstance().getPathPoints());
	}
	
	/**
	 * makes sure the getters and setters for GridDensity are working
	 * and will not let the density go below 1
	 */
	@Test
	public void testGridDensity() {
		Grid g = Grid.getInstance();
		g.setGridDensity(5);
		Assert.assertEquals(g.getGridDensity(), 5);
		
		g.setGridDensity(0);
		Assert.assertEquals(g.getGridDensity(), 1);//Grid should not let density drop below 1
	}
	
	/**
	 * ensures that the zoom in method only zooms in by one step
	 * will require testGridDensity() to pass
	 */
	@Test(dependsOnMethods = { "testGridDensity" })
	public void testZoomIn() {
		
	}
	
	/**
	 * TODO
	 */
	@Test
	public void testZoomOut() {
		
	}
	
	/**
	 * TODO
	 */
	@Test
	public void testHighlightedPoint() {
		
	}
	
	/**
	 * TODO
	 */
	@Test
	public void testLoadedFile() {
		
	}
}
