package edu.msoe.se2800.h4;

import com.google.common.io.Closeables;

import java.awt.Point;
import java.io.*;
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

    /**
     * Parses the given file for points and assigns those points to the points variable. If there is an error while
     * parsing or the file or the file could not be found, an exception is thrown and the points variable remains
     * as it was before entering this method.
     *
     * @param input The file containing the coordinates
     * @throws UnsupportedEncodingException Thrown if there was an error while parsing because of bad file format
     * @throws FileNotFoundException        Thrown if the file does not exist
     */
    public void readFromFile(File input) throws UnsupportedEncodingException, FileNotFoundException {
        // check to make sure the input argument is valid
        if (input == null) {
            throw new NullPointerException();
        } else if (!input.exists()) {
            throw new FileNotFoundException();
        }

        //create a temporary list to hold the points in the file
        List<Point> tempPoints = new ArrayList<Point>();

        try {
            // set up the reader variables
            FileInputStream fs = new FileInputStream(input);
            DataInputStream in = new DataInputStream(fs);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line;
            int lineCount = 1;
            boolean error = false;
            // while there are still lines to parse
            while ((line = br.readLine()) != null) {
                // verify that the first line is the predefined header
                if (lineCount == 1) {
                    if (!line.equals("some defined header")) {
                        error = true;
                    }

                } else {
                    // not on the first line so parse the coordinates. x and y separated by a ,
                    String[] coordinatesAsString = line.split(",");
                    if (coordinatesAsString.length != 2) {
                        error = true;
                    } else {
                        try {
                            // parse the points as integers and add to the temporary points list
                            int x = Integer.parseInt(coordinatesAsString[0]);
                            int y = Integer.parseInt(coordinatesAsString[1]);
                            tempPoints.add(new Point(x, y));
                        } catch (Exception e) {
                            error = true;
                        }

                    }

                }

                if (error) {
                    throw new UnsupportedEncodingException("File has corrupt data. Line " + lineCount + " was not in the correct format");
                }

                lineCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // if at this point, no error has ocurred. Set this.points to the tempPoints
        points = tempPoints;
    }

}
