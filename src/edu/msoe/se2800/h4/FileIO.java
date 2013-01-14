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
	 * A simple JFileChooser for saving and opening paths.
	 */
	private static JFileChooser chooser;
	/**
	 * A file filter to filter out all files except .scrumbot
	 */
    private static FileNameExtensionFilter filter;
    /**
     * A open button
     */
    private static JButton open = new JButton("open");
    /**
     * A save button
     */
    private static JButton save = new JButton("save");
    /**
     * A text field to prompt user to open or save
     */
    private static JTextField display = new JTextField();
    /**
     * The file selected by the user
     */
    private static File file;
    
	/**
	 * Opens a JFileChooser and returns the file to be opened.
	 * @author koenigj
	 *
	 */
	public static File open() throws FileNotFoundException{
	    
	    	JFrame directory = new JFrame();
			JFileChooser chooser = new JFileChooser();
			filter = new FileNameExtensionFilter("Robot files", "scrumbot");
			chooser.setFileFilter(filter);
			int value = chooser.showOpenDialog(directory);
			if (value == JFileChooser.APPROVE_OPTION) {
				file = chooser.getSelectedFile();
				System.out.println("file is opened");
			}    		
			if(file == null){
				return file;
			} else {
				throw new FileNotFoundException();
			}
		
	 }
	 
	  
	/**
	 * Testing out the ui.
	 * @param args
	 */
	public static void main(String[] args){
		try {
			FileIO.save();
			FileIO.open();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Returns the file to be saved.
	 * @return
	 * @throws FileNotFoundException
	 */
	public static File save() throws FileNotFoundException{
		JFrame directory = new JFrame();
		JFileChooser chooser = new JFileChooser();
		filter = new FileNameExtensionFilter("Robot files", "scrumbot");
		chooser.setFileFilter(filter);
		int value = chooser.showSaveDialog(directory);
		if (value == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
			System.out.println("file is opened");
		}    		
		if(file == null){
			return file;
		} else {
			throw new FileNotFoundException();
		}
	}
}
