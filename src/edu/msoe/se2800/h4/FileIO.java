package edu.msoe.se2800.h4;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Temporary class for saving and writing path files
 * @author koenigj
 *
 */
public class FileIO {
	
	/**
	 * A file filter to filter out all files except .scrumbot
	 */
    private static FileNameExtensionFilter filter;
  
    /**
     * The file selected by the user
     */
    private static File file;
    
	/**
	 * Opens a JFileChooser and returns the file to be opened.
	 * @author koenigj
	 *
	 */
	public static File open(){
	    
	    	JFrame directory = new JFrame();
			JFileChooser chooser = new JFileChooser();
			filter = new FileNameExtensionFilter("Robot files", "scrumbot");
			chooser.setFileFilter(filter);
			int value = chooser.showOpenDialog(directory);
			if (value == JFileChooser.APPROVE_OPTION) {
				file = chooser.getSelectedFile();
				System.out.println("file is opened");
			}    		
			return file;
		
	 }
	
	/**
	 * Returns the file to be saved.
	 * @return
	 * @throws FileNotFoundException
	 */
	public static File save(){
		JFrame directory = new JFrame();
		JFileChooser chooser = new JFileChooser();
		filter = new FileNameExtensionFilter("Robot files", "scrumbot");
		chooser.setFileFilter(filter);
		int value = chooser.showSaveDialog(directory);
		if (value == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
			System.out.println("file is opened");
		}    		
		return file;
	}
}
