
package edu.msoe.se2800.h4;

import static com.google.common.base.Preconditions.checkNotNull;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public enum Path {

    INSTANCE;

    private List<Point> points = new ArrayList<Point>();

    public boolean writeToFile(File outputFile) {
        checkNotNull(outputFile, "The File instance passed was null");
        boolean threwException = true;
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(outputFile);
            writer.println("## The points in this file are represented as <x-value, y-value>. ##");
            for (Point point : points) {
                writer.println(point.getX());
                writer.println(point.getY());
            }
            threwException = false;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
        return threwException;
    }

    public void readFromFile(File input) {

    }
    
    public void reset() {
        points.clear();
    }

}
