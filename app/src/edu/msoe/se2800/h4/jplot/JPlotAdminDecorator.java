package edu.msoe.se2800.h4.jplot;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import edu.msoe.se2800.h4.administrationFeatures.CreateUserUI;

public class JPlotAdminDecorator extends JPlotDecorator {

	public JPlotAdminDecorator(JPlotInterface jplot) {
		super(jplot);
	}

	@Override
	public void initSubviews() {
		jplot.initSubviews();
		JMenu jMenuAdmin = new JMenu();
        jMenuAdmin.setText("Administration");

        JMenuItem mnuCreateNew = new JMenuItem();
        mnuCreateNew.setText("Create User");
        mnuCreateNew.setActionCommand("create_user");
        mnuCreateNew.setName("create_user");
        mnuCreateNew.addActionListener(new MenuActionListener());

        JMenuItem mnuList = new JMenuItem();
        mnuList.setText("List Users");
        mnuList.setActionCommand("list_user");
        mnuList.setName("list_user");
        mnuList.addActionListener(new MenuActionListener());

        jMenuAdmin.add(mnuCreateNew);
        jMenuAdmin.add(mnuList);
        
		getFrame().getJMenuBar().add(jMenuAdmin);
	}
	
	public class MenuActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("create_user")) {
                CreateUserUI createUserUI = new CreateUserUI(JPlotController.getInstance());
                createUserUI.setVisible(true);
            	//TODO @andrew get the username, password, and role from the gui
            	//TODO @andrew pass them to the controller JPlotController.getInstance().createUser(user, pass, role);
            	//TODO also you need to change the createUser method in JPlotController to accept the parameters
            } else if (e.getActionCommand().equals("list_user")) {
                JPlotController.getInstance().listUsers();
            }
        }

    }

}
