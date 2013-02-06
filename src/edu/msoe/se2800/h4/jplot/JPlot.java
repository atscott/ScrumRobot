package edu.msoe.se2800.h4.jplot;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

import edu.msoe.se2800.h4.administrationFeatures.CreateUserUI;
import edu.msoe.se2800.h4.administrationFeatures.DatabaseConnection;
import edu.msoe.se2800.h4.jplot.grid.AdminGridDecorator;
import edu.msoe.se2800.h4.jplot.grid.GridInterface;
import edu.msoe.se2800.h4.jplot.grid.ImmediateGridDecorator;

public class JPlot extends JFrame {

    /**
     * Generated serialVersionUID
     */
    private static final long serialVersionUID = -8344597455042452839L;

    public JPlot(DatabaseConnection.UserTypes mode, GridInterface grid) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("JPlot - " + mode);
        getContentPane().setPreferredSize(new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));

        if (mode != DatabaseConnection.UserTypes.OBSERVER) {
            Constants.INFO_PANEL_WIDTH = 150;
            if (mode == DatabaseConnection.UserTypes.ADMIN) {
                grid = new AdminGridDecorator(grid);
            } else if (mode == DatabaseConnection.UserTypes.OTHER) {//TODO used to be immediate
                grid = new ImmediateGridDecorator(grid);
            }
        }

        grid.initSubviews();

        getContentPane().add(grid.getComponent());

        /** Mode Changing Menu */
        JMenuBar jMenuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem logoutItem = new JMenuItem("Log out");
        logoutItem.setName("logout");
        logoutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JPlotController.getInstance() != null) {
                    JPlotController.getInstance().logOut();
                    JPlotController.getInstance().start(JPlotController.getInstance().robotController);
                }else{
                    JPlot.this.dispose();
                }
            }
        });
        fileMenu.add(logoutItem);
        jMenuBar.add(fileMenu);

        JMenu jMenuMode = new JMenu();
        jMenuMode.setText("Operating Mode");
        JMenuItem mnuObserver = new JMenuItem();
        mnuObserver.setText("Observer Mode");
        mnuObserver.setActionCommand("observer");
        mnuObserver.setName("observer_mode");
        mnuObserver.addActionListener(new MenuActionListener());

        JMenuItem mnuImmediate = new JMenuItem();
        mnuImmediate.setText("Immediate Mode");
        mnuImmediate.setActionCommand("immediate");
        mnuImmediate.setName("immediate_mode");
        mnuImmediate.addActionListener(new MenuActionListener());

        JMenuItem mnuAdministrator = new JMenuItem();
        mnuAdministrator.setText("Administrator Mode");
        mnuAdministrator.setActionCommand("administrator");
        mnuAdministrator.setName("administrator_mode");
        mnuAdministrator.addActionListener(new MenuActionListener());

        jMenuMode.add(mnuObserver);
        jMenuMode.add(mnuImmediate);
        jMenuMode.add(mnuAdministrator);

        /** Administrative features menu */
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

        jMenuBar.add(jMenuMode);
        jMenuBar.add(jMenuAdmin);
        setJMenuBar(jMenuBar);

        pack();
        setVisible(true);
    }

    public class MenuActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equalsIgnoreCase("observer")) {
                System.out.println("you chose observer mode");
                JPlotController.getInstance().changeMode(DatabaseConnection.UserTypes.OBSERVER);
            } else if (e.getActionCommand().equalsIgnoreCase("immediate")) {
                System.out.println("you chose immediate mode");
                JPlotController.getInstance().changeMode(DatabaseConnection.UserTypes.OTHER);
            } else if (e.getActionCommand().equalsIgnoreCase("administrator")) {
                System.out.println("you chose administrator mode");
                JPlotController.getInstance().changeMode(DatabaseConnection.UserTypes.ADMIN);
            } else if (e.getActionCommand().equals("create_user")) {
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
