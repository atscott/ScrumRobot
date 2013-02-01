
package edu.msoe.se2800.h4;

import edu.msoe.se2800.h4.jplot.JPlotController;

import java.io.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Temporary class for saving and writing path files
 *
 * @author koenigj
 */
public abstract class FileIO {

    /**
     * A file filter to filter out all files except .scrumbot
     */
    private static FileNameExtensionFilter filter;

    /**
     * Opens a JFileChooser and returns the file to be opened.
     *
     * @return file, null if cancel is pressed
     * @author koenigj
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
     * Returns the file to be saved.
     *
     * @return file, null if cancel is pressed
     * @throws FileNotFoundException
     */
    public static File showSaveDialog() {
        JFrame directory = new JFrame();
        JFileChooser chooser = new JFileChooser();
        filter = new FileNameExtensionFilter("Robot files", "scrumbot");
        chooser.setFileFilter(filter);
        chooser.showSaveDialog(directory);
        File file = chooser.getSelectedFile();
        String text = file.getAbsolutePath();
        if (text.endsWith(".scrumbot")) {
            file = new File(text + ".scrumbot");
        }
        return file;
    }

    /**
     * @param path indicates where to showSaveDialog the path to. If null, a showSaveDialog as dialog appears
     * @return indicates where the path was saved to. If null, the showSaveDialog was cancelled. Since the File class
     *         is immutable, the argument cannot simply be changed.
     */
    public static File saveTo(File path) {
        // If performing showSaveDialog as... show the file chooser
        if (path == null) {
            File toSave = FileIO.showSaveDialog();

            // Save the fact that we nowhave a file for future use
            if (!toSave.getPath().endsWith(".scrumbot")) {
                toSave = new File(toSave.getPath() + ".scrumbot");
            }

            path = toSave;
        } else {
            try {
                JPlotController.getInstance().getPath()
                        .dumpObject(new DataOutputStream(new FileOutputStream(path)));

                JOptionPane.showMessageDialog(null, "Path saved succesfully!");
            } catch (FileNotFoundException e1) {
                // Already handled in the FileIO
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(null,
                        "An unknown error occurred while saving the Path.", "Uh-oh!",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        return path;
    }

    /**
     *
     * @param currentlyLoaded The currently loaded path file on the grid. if this is not null then a save prompt has
     *                        to be made.
     * @return The File that was chosen to load
     */
    public static File load(File currentlyLoaded) {
        File savedTo = null;

        //if currently editing a file or the path on the grid is not empty
        if (currentlyLoaded != null || !JPlotController.getInstance().getPath().isEmpty()) {
            int result = JOptionPane
                    .showConfirmDialog(null, "Do you wish to save your current Path?", "Save...?",
                            JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                FileIO.saveTo(currentlyLoaded);
            }
        }

        File tempPathFile = FileIO.showOpenDialog();
        if (tempPathFile != null) {
            try {
                JPlotController.getInstance().getPath()
                        .loadObject(new DataInputStream(new FileInputStream(tempPathFile)));
                savedTo = tempPathFile;
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(null, "Unable to access the file.",
                        "Uh-oh!", JOptionPane.ERROR_MESSAGE);
            }
            JPlotController.getInstance().getGrid().redraw();
        }

        return savedTo;
    }
}
