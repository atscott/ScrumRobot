package edu.msoe.se2800.h4;

import org.testng.Assert;
import org.testng.annotations.Test;

import edu.msoe.se2800.h4.jplot.JPoint;


@Test
public class JPointTest {

	/**
	 * Checking the custom toString method.
	 */
	@Test
	public void testToString() {
		JPoint p = new JPoint(1,2);
		Assert.assertEquals("1, 2",p.toString());		
	}
}
