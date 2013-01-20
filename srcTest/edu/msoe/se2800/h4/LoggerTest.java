package edu.msoe.se2800.h4;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: tohtzk
 * Date: 1/15/13
 * Time: 10:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class LoggerTest {
    private Logger logs;

    @BeforeMethod
    public void setupLogger() {
        logs = Logger.INSTANCE;
    }

    /**
     * Testing for the existence of the output.txt
     */
    @Test
    public void validLogOutputFile() {
        File f = new File("output.log");
        if(f.exists()){
            f.delete();
        }
        logs.log("testing", "testingprint");
        Assert.assertTrue(f.exists());
    }

    /**
     * Testing that the given phrase was parsed correctly
     */
    @Test
    public void validPrintInFile() throws FileNotFoundException {

        logs.log("testing", "testingprint");
        File f = new File("output.log");
        Scanner scan = new Scanner(f);
        String s = scan.next();
        Assert.assertEquals(s, "testingprint");

    }

    @AfterClass
    public void finish() {
        logs = null;
    }

}
