package edu.msoe.se2800.h4.jplot.grid;

import org.testng.annotations.BeforeClass;

public class ImmediateGridDecoratorTest {
	
private Grid g = new Grid();
private ImmediateGridDecorator grid = new ImmediateGridDecorator(g);
	
	@BeforeClass
    public void setupGridTesting() {
		
		// I looked at the grid test and it doesn't test the one method in ImmediateGridDecoratorTests.
		// Except this before class method. Not sure if we really need this.
		
		
		
        // Ensure that we dont get null pointer exceptions from Grid child views not being initialized
        grid.initSubviews();
    }
}
