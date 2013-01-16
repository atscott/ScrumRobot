package edu.msoe.se2800.h4;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
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
    public void setupLogger(){
        logs = Logger.INSTANCE;
    }

    /**
     * Testing for attempting to print with a null logger
     * expecting null pointer exception
     */
    @Test(expectedExceptions = NullPointerException.class)
    public void nullLogger(){
        logs = null;
        logs.log("NullTest","NULLPRINT");
    }

    /**
     * Testing for the existance of the output.txt
     */
    @Test
    public void validLogOutputFile(){
        logs.log("testing","testingprint");
        File f = new File("output.log");
        Assert.assertTrue(f.exists());
    }

    /**
     * Testing that the given phrase was parsed correctly
     */
    @Test
    public void validPrintInFile(){
        try{
            logs.log("testing","testingprint");
            File f = new File("output.log");
            Scanner scan = new Scanner(f);
            String s = scan.next();
            Assert.assertEquals(s,"testingprint");
        } catch  (Exception e) {
            System.out.println("Shouldn't file i/o print");
        }
    }

    @AfterClass
    public void finish(){
        logs = null;
    }

}
