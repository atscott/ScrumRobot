
package edu.msoe.se2800.h4;

import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Temporary class for saving and writing path files
 * 
 * @author koenigj
 */
public class FileIO {

    /**
     * A file filter to filter out all files except .scrumbot
     */
    private static FileNameExtensionFilter filter;

    /**
     * Opens a JFileChooser and returns the file to be opened.
     * @return file, null if cancel is pressed
     * @author koenigj
     */
    public static File open() {

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
    public static File save() {
        JFrame directory = new JFrame();
        JFileChooser chooser = new JFileChooser();
        filter = new FileNameExtensionFilter("Robot files", "scrumbot");
        chooser.setFileFilter(filter);
        chooser.showSaveDialog(directory);
        File file = chooser.getSelectedFile();
        String text = file.getAbsolutePath();
        if(text.endsWith(".scrumbot")){
        	file = new File(text+".scrumbot");
        }
        return file;
    }
}
