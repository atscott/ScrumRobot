package edu.msoe.se2800.h4;

import com.google.common.io.Closeables;

import java.awt.Point;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public enum Path {
    
    INSTANCE;

	private List<Point> points = new ArrayList<Point>();
	
	public void writeToFile(File outputFile) throws IOException {
	    boolean threwException = true;
	    Writer writer = null;	    
	    try {
	        writer = new BufferedWriter(new FileWriter(outputFile));
	        for (Point point : points) {
	            
	        }
	        threwException = false;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
	        Closeables.close(writer, threwException);
	    }
	}
	
	public void readFromFile(File input) {
	    
	}

}
