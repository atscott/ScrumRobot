package edu.msoe.se2800.h4;

import java.io.File;
import java.io.FileNotFoundException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


@Test
public class FileIOTest {

	private FileIO io;
	
	@BeforeClass public void setUp(){
		io = new FileIO();
	}
	
//	@Test
//	public void nullFile(){
//		try {
//			//File file = io.getFile();
//		} catch (FileNotFoundException e) {
//			Assert.assertTrue(true);
//		}
//		
//	}
	
}
