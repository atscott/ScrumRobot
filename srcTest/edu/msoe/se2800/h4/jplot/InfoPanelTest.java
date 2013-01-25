package edu.msoe.se2800.h4.jplot;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import edu.msoe.se2800.h4.jplot.InfoPanel;

public class InfoPanelTest {
	
	private InfoPanel infoPanel;
	
	@BeforeClass
    public void setupGridTesting() {
        infoPanel = new InfoPanel();
    }
	
	@Test
	public void testInitSubviews() {
		infoPanel.initSubviews();
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
	}*/

}
