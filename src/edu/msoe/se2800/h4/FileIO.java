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
	private JFileChooser chooser;
	/**
	 * A file filter to filter out all files except .scrumbot
	 */
    private FileNameExtensionFilter filter;
    /**
     * A open button
     */
    private JButton open = new JButton("open");
    /**
     * A save button
     */
    private JButton save = new JButton("save");
    /**
     * A text field to prompt user to open or save
     */
    private JTextField display = new JTextField();
    /**
     * The file selected by the user
     */
    private File file;
    
	/**
	 * A dialog for opening or saving. Buttons have action listeners provided by the inner classes below
	 */
	public FileIO(){
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		open.addActionListener(new Open());
		panel.add(open);
		save.addActionListener(new Save());
		panel.add(save);
		frame.add(panel);
		display.setEditable(false);
		display.setText("Do you wanna load or save a file?");
		panel = new JPanel();
		panel.setLayout(new GridLayout(2,1));
		panel.add(display);
		frame.add(panel, BorderLayout.NORTH);
		frame.setSize(300, 200);
		frame.setVisible(true);
	}
	/**
	 * Opens a JFileChooser with a .scrumbot filter
	 * @author koenigj
	 *
	 */
	private class Open implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	    	JFrame directory = new JFrame();
	    	JFileChooser chooser = new JFileChooser();
	    	filter = new FileNameExtensionFilter("Robot files", "scrumbot");
			chooser.setFileFilter(filter);
			int value = chooser.showOpenDialog(directory);
	    	 if (value == JFileChooser.APPROVE_OPTION) {
	             file = chooser.getSelectedFile();
	             System.out.println("file is opened");
	    	 }    
	    }
	 }
	 /**
	  * Opens a JFileChooser with a .scrumbot filter
	  * @author koenigj
	  *
	  */
	  private class Save implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	    	JFrame directory = new JFrame();
	    	JFileChooser chooser = new JFileChooser();
	    	filter = new FileNameExtensionFilter("Robot files", "scrumbot");
			chooser.setFileFilter(filter);
			int value = chooser.showOpenDialog(directory);
	    	 if (value == JFileChooser.APPROVE_OPTION) {
	             file = chooser.getSelectedFile();
	             System.out.println("file is opened");
	    	 }    
	    }
	  }
	/**
	 * Testing out the ui.
	 * @param args
	 */
	public static void main(String[] args){
		FileIO run = new FileIO();
	}
	/**
	 * Returns the file.
	 * @return
	 * @throws FileNotFoundException
	 */
	public File getFile() throws FileNotFoundException{
		if(file == null){
			return file;
		} else {
			throw new FileNotFoundException();
		}
	}
}
