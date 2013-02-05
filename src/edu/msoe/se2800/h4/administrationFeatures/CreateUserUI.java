
package edu.msoe.se2800.h4.administrationFeatures;

import edu.msoe.se2800.h4.jplot.JPlotController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class CreateUserUI extends JDialog {

    /**
     * TextField for the username
     */
    JTextField usernameField;

    /**
     * PasswordField for the password
     */
    JPasswordField passwordField;

    /**
     * must match the passwordField
     */
    JPasswordField confirmPasswordField;

    /**
     * String version of the username
     */
    String username = "";

    /**
     * String version of the password
     */
    String password = "";

    private boolean successfullLogin = false;

    /**
     * Char array of the password
     */

    JButton confirmBtn;
    JPanel panel;
    JButton cancelBtn;
    JPlotController controller;
    JComboBox userTypeCmb;

    /**
     * Constructor that initializes the UI
     */
    public CreateUserUI(JPlotController controller) {
        this.controller = controller;
        setModal(true);
        initComponents();
    }

    /**
     * Allows for this to be used as a modal dialog for a parent JFrame
     *
     * @param parent
     */
    public CreateUserUI(JFrame parent, JPlotController controller) {
        super(parent);
        setModal(true);
        initComponents();
    }

    private void initComponents() {
        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));
        JLabel usernameLabel = new JLabel("Username: ");
        JLabel passwordLabel = new JLabel("Password: ");
        JLabel confirmPasswordLabel = new JLabel("Confirm Password: ");
        JLabel userTypeLabel = new JLabel("User Type: ");
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        confirmPasswordField = new JPasswordField(15);

        confirmBtn = new JButton("Create user");
        cancelBtn = new JButton("Cancel");
        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getPassword().equals(getConfirmPassword())) {
                    ResultInfo rs = controller.createUser(getUsername(), getPassword(), getUserTypeCmb());
                    if (rs.wasSuccess()) {
                        CreateUserUI.this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(CreateUserUI.this, rs.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(CreateUserUI.this, "Passwords do not match.");
                }
            }
        });
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateUserUI.this.dispose();
            }
        });
        String[] items = {"Observer", "Programmer", "Administrator"};
        userTypeCmb = new JComboBox(items);
        userTypeCmb.setSelectedIndex(0);

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(confirmPasswordLabel);
        panel.add(confirmPasswordField);
        panel.add(userTypeLabel);
        panel.add(userTypeCmb);
        panel.add(confirmBtn);
        panel.add(cancelBtn);


        this.add(panel);
        this.setTitle("Create user");
        this.setResizable(false);
        this.setSize(300, 150);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getRootPane().setDefaultButton(confirmBtn);
    }


    /**
     * Method that returns the username
     *
     * @return String username
     */
    public String getUsername() {
        return usernameField.getText();
    }

    /**
     * @return true if the user successfully logged in before dialog closed
     */
    public boolean wasLoginSuccessful() {
        return successfullLogin;
    }

    /**
     * Method that returns the password
     *
     * @return String password
     */
    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public String getConfirmPassword() {
        return new String(confirmPasswordField.getPassword());
    }

    public DatabaseConnection.UserTypes getUserTypeCmb() {
        switch(userTypeCmb.getSelectedIndex()){
            case 0:
                return DatabaseConnection.UserTypes.OBSERVER;
            case 1:
                return DatabaseConnection.UserTypes.PROGRAMMER;
            default:
                return DatabaseConnection.UserTypes.ADMIN;
        }
    }


}
