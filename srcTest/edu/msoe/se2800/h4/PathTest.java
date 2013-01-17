//TODO consider getting rid of these tests. our implementation just calls the underlying structure so these tests are pretty useless. It might make more sense to just make a inline comment in each method in the Path class to create a test if updating it.

package edu.msoe.se2800.h4;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import edu.msoe.se2800.h4.jplot.JPoint;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PathTest {

    private Path mPath = Path.INSTANCE;

    @BeforeClass
    public void setupPath() {

        // Ensure we have a fresh start
        mPath.reset();

        // add 10 points along a straight line
        for (int i = 0; i < 10; i++) {
            mPath.add(new JPoint(i * 10, i * 10));
        }
    }

    @BeforeMethod
    public void beforeMethod() {
        mPath.reset();
    }

    @Test(description = "")
    // TODO @Macon add a description
    public void testReset() {
        mPath.add(new JPoint());
        mPath.add(new JPoint());
        mPath.reset();
    }

    @Test(description = "")
    // TODO @Macon add a description
    public void testAdd() {
        JPoint point1 = new JPoint();
        assertTrue(mPath.add(point1));
    }

    @Test(description = "")
    // TODO @Macon add a description
    public void testGetNull() {
        assertEquals(null, mPath.get(0));
    }

    @Test(description = "")
    // TODO @Macon add a description
    public void testGet() {
        JPoint point = new JPoint();
        assertEquals(point, mPath.get(0));
    }

    @Test(description = "")
    // TODO @Macon add a description
    public void testSize() {
        mPath.add(new JPoint());
        int count = 1;
        assertEquals(mPath.size(), count);

    }

    @AfterClass
    public void resetPath() {
        mPath.reset();
    }

}
