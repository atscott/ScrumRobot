
package edu.msoe.se2800.h4;

import edu.msoe.se2800.h4.jplot.JPlotController;

import java.io.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Provides utilities for saving and writing path files
 * 
 * @author Team .scrumbot
 */
public abstract class FileIO {

    /**
     * A file filter to filter out all files except .scrumbot
     */
    private static FileNameExtensionFilter filter;

    private static File sPathFile = null;

    /**
     * Opens a JFileChooser and returns the file to be opened.
     * 
     * @author koenigj
     * @return file, null if cancel is pressed
     */
    public static File showOpenDialog() {

        JFrame directory = new JFrame();
        JFileChooser chooser = new JFileChooser();
        filter = new FileNameExtensionFilter("Robot files", "scrumbot");
        chooser.setFileFilter(filter);
        chooser.showOpenDialog(directory);
        return chooser.getSelectedFile();

    }

    /**
     * @author marius
     * @return File representing the current Path file
     */
    public static File getCurrentPathFile() {
        return sPathFile;
    }

    /**
     * Displays a JFileChooser from which the user can select a location at which to save a Path.
     * The saved file is standardized to have a .scrumbot extension.
     * 
     * @author koenigj
     * @return File standardized to have a .scrumbot extension. null if cancel is selected
     */
    public static File showSaveDialog() {
        JFrame directory = new JFrame();
        JFileChooser chooser = new JFileChooser();
        filter = new FileNameExtensionFilter("Robot files", "scrumbot");
        chooser.setFileFilter(filter);
        chooser.showSaveDialog(directory);
        File file = chooser.getSelectedFile();

        // Append the .scrumbot extension
        if (file != null) {
            String text = file.getAbsolutePath();
            if (text.endsWith(".scrumbot")) {
                file = new File(text + ".scrumbot");
            }
        }
        return file;
    }

    /**
     * Convenience method to provide the ability to select where to save the file before writing to
     * disk.
     * 
     * @author marius
     */
    public static void saveAs() {
        File toSave = FileIO.showSaveDialog();

        if (toSave != null) {

            // Save the fact that we now have a file for future use
            sPathFile = toSave;
            FileIO.save();
        }

    }

    /**
     * Makes a best-effort attempt at writing the Path to disk. Provides feedback to the user on the
     * state of the write.
     * 
     * @author marius
     */
    public static void save() {

        // If we don't yet have a File, force a save as...
        if (sPathFile == null) {
            FileIO.saveAs();
        } else {
            try {
                JPlotController.getInstance().getPath()
                        .dumpObject(new DataOutputStream(new FileOutputStream(sPathFile)));

                JOptionPane.showMessageDialog(null, "Path saved succesfully!");
            } catch (FileNotFoundException e1) {
                JOptionPane.showMessageDialog(null,
                        "An unknown error occurred while saving the Path.", "Uh-oh!",
                        JOptionPane.ERROR_MESSAGE);
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(null,
                        "An unknown error occurred while saving the Path.", "Uh-oh!",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Reads a Path from disk and loads it into the Grid
     * 
     * @author marius
     */
    public static void load() {

        // if currently editing a file or the path on the grid is not empty
        if (sPathFile != null || !JPlotController.getInstance().getPath().isEmpty()) {
            int result = JOptionPane
                    .showConfirmDialog(null, "Do you wish to save your current Path?", "Save...?",
                            JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                FileIO.save();
            }
        }

        File tempPathFile = FileIO.showOpenDialog();
        if (tempPathFile != null) {
            try {
                JPlotController.getInstance().getPath()
                        .loadObject(new DataInputStream(new FileInputStream(tempPathFile)));
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(null, "Unable to access the file.",
                        "Uh-oh!", JOptionPane.ERROR_MESSAGE);
            }
            JPlotController.getInstance().getGrid().redraw();
            sPathFile = tempPathFile;
        }

    }
}
