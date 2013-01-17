
package edu.msoe.se2800.h4;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA. User: tohtzk Date: 1/15/13 Time: 10:19 AM To change this template use
 * File | Settings | File Templates.
 */
public class LoggerTest {
    private Logger logs;

    @BeforeMethod
    public void setupLogger() {
        logs = Logger.INSTANCE;
    }

    @Test(expectedExceptions = NullPointerException.class, description = "Testing for attempting to print with a null logger expecting null pointer exception")
    public void nullLogger() {
        logs = null;
        logs.log("NullTest", "NULLPRINT");
        // FIXME @Kevin this test doesn't test any functionality.
    }

    @Test(description = "Testing for the existence of the output.txt")
    public void validLogOutputFile() {
        logs.log("testing", "testingprint");
        File f = new File("output.log");
        Assert.assertTrue(f.exists());
        // FIXME @Kevin make sure to delete the file at the start of this test. otherwise you don't
        // know if it was there already or not
    }

    @Test(description = "Testing that the given phrase was parsed correctly")
    public void validPrintInFile() {
        // TODO @Kevin don't catch the exception. 2 reasons for this. 1. we don't see calls to
        // system.out in the logs. you see them in the console but not in html. 2. if an exception
        // occurs, throw it because the test will fail then which is the behavior we want
        try {
            logs.log("testing", "testingprint");
            File f = new File("output.log");
            Scanner scan = new Scanner(f);
            String s = scan.next();
            Assert.assertEquals(s, "testingprint");
        } catch (Exception e) {
            System.out.println("Shouldn't file i/o print");
        }
    }

}
