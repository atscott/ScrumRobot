package edu.msoe.se2800.h4;

import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DropMode;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.WindowConstants;

public class UserListUI extends JDialog {

	/** Generated serialVersionUID */
	private static final long serialVersionUID = -6457636746676490029L;
	
	private UserListController controller;
	
	private final Container mainPanel;
	
	public UserListUI(UserListController controller) {
		this.controller = controller;
		
		mainPanel = getContentPane();
		
		initComponents();
        setModal(true);
        setResizable(false);
        setSize(750, 700);
        
        // dispose when exit is clicked
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
	}
	
	private void initComponents() {
		JTable table = new JTable();
		
		table.setDragEnabled(true);
		table.setDropMode(DropMode.INSERT_ROWS);
		table.setTransferHandler(new TableRowTransferHandler(table));
		
		mainPanel.add(table);
    }

}
