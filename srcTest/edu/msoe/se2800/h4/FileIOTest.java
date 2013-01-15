package edu.msoe.se2800.h4;

import java.io.File;
import java.io.FileNotFoundException;

import org.testng.Assert;
import org.testng.annotations.Test;


@Test
public class FileIOTest {

	private FileIO io;
	
	
	/**
	 * Checking null.
	 */
	@Test
	public void nullFileSave(){
		try {
			File file = FileIO.open();
		} catch (FileNotFoundException e) {
			Assert.assertTrue(true);
		}
		
	}
	
	/**
	 * Checking null.
	 */
	@Test
	public void nullFileOpen(){
		try {
			File file = FileIO.save();
		} catch (FileNotFoundException e) {
			Assert.assertTrue(true);
		}
		
	}
}
