
package edu.msoe.se2800.h4;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Testing the logger Class
 * @author tohtzk
 * Date: 1/15/13
 * Time: 10:19 AM To change this template use
 * File | Settings | File Templates.
 */
public class LoggerTest {
    private Logger logs;

    /**
     * Gets the instance of the logger before every test
     */
    @BeforeClass
    public void setupLogger() {
        logs = Logger.INSTANCE;
    }

    /**
     * Deletes the file before each test
     */
    @BeforeMethod
    public void setupTestEnv() {
        File f = new File(Logger.FILE_NAME);
        if (f.exists()) {
            f.delete();
        }
    }

    /**
     * Testing for the existence of the output.txt
     */
    @Test(description = "This is testing to see that the correct log file is being written")
    public void validLogOutputFile() {
        File f = new File(Logger.FILE_NAME);
        logs.log("LoggerTest", "testingprint");
        f = new File(Logger.FILE_NAME);
        Assert.assertTrue(f.exists());
    }

    /**
     * Testing that the given phrase was parsed correctly
     */
    @Test(description = "This is a test for writing normally in the log file")
    public void validPrintInFile() throws FileNotFoundException {
        DateFormat format = DateFormat.getInstance();
        String date = format.format(new Date());
        logs.log("LoggerTest", "testingprint");
        File f = new File(Logger.FILE_NAME);
        Scanner scan = new Scanner(f);
        String s = scan.nextLine().trim();
        Assert.assertEquals(s, date + " | " + "LoggerTest | " + "testingprint");
    }

    /**
     * Testing Log method for normal workflow.
     */
    @Test(description = "This is testing using the log method normally as intended")
    public void normalLogTest() throws FileNotFoundException {
        DateFormat format = DateFormat.getInstance();
        String date = format.format(new Date());
        String tag = "LoggerTest";
        String message = "This is a %s, %s";
        String[] args = new String[] {
                "%^$#%$#", "Test"
        };
        logs.log(tag, message, args);
        Scanner scan = new Scanner(new FileReader(Logger.FILE_NAME));
        String s = scan.nextLine();
        Assert.assertEquals(s, date + " | " + "LoggerTest | " + "This is a %^$#%$#, Test");
    }
}
