package edu.msoe.se2800.h4.jplot;

import java.awt.Component;

import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class InfoPanelTest {
	
	private InfoPanel infoPanel;
	
	@BeforeClass
    public void setupGridTesting() {
        infoPanel = new InfoPanel();
        infoPanel.initSubviews();
    }
	
	@Test
	public void testDisableSubviews() {
		for (Component c : infoPanel.getComponents()) {
			Assert.assertFalse(c.isEnabled());
		}
	}
}
