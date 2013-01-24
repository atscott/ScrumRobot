package edu.msoe.se2800.h4;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

public class UserListUI extends JDialog {

	/** Generated serialVersionUID */
	private static final long serialVersionUID = -6457636746676490029L;
	
	private UserListController controller;
	
	private JPanel contentPane;
    private JList listObservers, listProgrammers, listAdministrators;
    
    public UserListUI(List<String> observers, List<String> programmers, List<String> administrators, UserListController controller) {
    	this.controller = controller;
    	//Create and set up the window.
        setTitle("User List");
        setModal(true);
        
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        //leave the controller the responsibility of closing the dialog and updating users
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                UserListUI.this.controller.onClose((DefaultListModel)listObservers.getModel(), (DefaultListModel)listProgrammers.getModel(), (DefaultListModel)listAdministrators.getModel());
                dispose();
            }
        });

        setResizable(false);
        setPreferredSize(new Dimension(900,500));
        
        contentPane = new JPanel(new BorderLayout());
        JPanel leftPanel = createVerticalBoxPanel();
        JPanel centerPanel = createVerticalBoxPanel();
        JPanel rightPanel = createVerticalBoxPanel();

        //Create a table model.
        DefaultListModel lmObservers = new DefaultListModel();
        for (String s : observers) {
        	lmObservers.addElement(s);
        }
        
        //Create a table model.
        DefaultListModel lmProgrammers = new DefaultListModel();
        for (String s : programmers) {
        	lmProgrammers.addElement(s);
        }
        
        //Create a table model.
        DefaultListModel lmAdministrators = new DefaultListModel();
        for (String s : administrators) {
        	lmAdministrators.addElement(s);
        }

        //LEFT COLUMN
        listObservers = new JList(lmObservers);
        listObservers.setDragEnabled(true);
        listObservers.setPreferredSize(new Dimension(300,500));
        listObservers.setDropMode(DropMode.INSERT);
        listObservers.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listObservers.setTransferHandler(new ListTransferHandler(listObservers));
        leftPanel.add(createPanelForComponent(listObservers, "Observers"));
        
        //CENTER COLUMN
        listProgrammers = new JList(lmProgrammers);
        listProgrammers.setDragEnabled(true);
        listProgrammers.setPreferredSize(new Dimension(300,500));
        listProgrammers.setDropMode(DropMode.INSERT);
        listProgrammers.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listProgrammers.setTransferHandler(new ListTransferHandler(listProgrammers));
        centerPanel.add(createPanelForComponent(listProgrammers, "Programmers"));
        
        //RIGHT COLUMN
        listAdministrators = new JList(lmAdministrators);
        listAdministrators.setDragEnabled(true);
        listAdministrators.setPreferredSize(new Dimension(300,500));
        listAdministrators.setDropMode(DropMode.INSERT);
        listAdministrators.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listAdministrators.setTransferHandler(new ListTransferHandler(listAdministrators));
        rightPanel.add(createPanelForComponent(listAdministrators, "Administrators"));
        
        contentPane.add(leftPanel, BorderLayout.WEST);
        contentPane.add(centerPanel, BorderLayout.CENTER);
        contentPane.add(rightPanel, BorderLayout.EAST);
        contentPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        
        contentPane.setOpaque(true); //content panes must be opaque
        setContentPane(contentPane);
        
        //Display the window.
        pack();
        setVisible(true);
    }
    
    public DefaultListModel getObserversListModel() {
    	return (DefaultListModel)listObservers.getModel();
    }
    
    public DefaultListModel getProgrammersListModel() {
    	return (DefaultListModel)listProgrammers.getModel();
    }
    
    public DefaultListModel getAdministratorsListModel() {
    	return (DefaultListModel)listAdministrators.getModel();
    }

    protected JPanel createVerticalBoxPanel() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
        p.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        return p;
    }

    public JPanel createPanelForComponent(JComponent comp, String title) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(comp, BorderLayout.CENTER);
        if (title != null) {
            panel.setBorder(BorderFactory.createTitledBorder(title));
        }
        return panel;
    }

}
