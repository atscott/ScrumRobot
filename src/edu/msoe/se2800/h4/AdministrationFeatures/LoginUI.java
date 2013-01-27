
package edu.msoe.se2800.h4.AdministrationFeatures;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

@SuppressWarnings("serial")
public class LoginUI extends JDialog {

    /**
     * TextField for the username
     */
    JTextField usernameField;

    /**
     * PasswordField for the password
     */
    JPasswordField passwordField;

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
    char[] passwordArray;

    JButton loginBtn;

    /**
     * Constructor that initializes the UI
     */
    public LoginUI() {
        setModal(true);
        initComponents();
    }

    /**
     * Allows for this to be used as a modal dialog for a parent JFrame
     * @param parent
     */
    public LoginUI(JFrame parent){
        super(parent);
        setModal(true);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        JLabel usernameLabel = new JLabel("Username: ");
        JLabel passwordLabel = new JLabel("Password: ");
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);

        loginBtn = new JButton("Login");
        JButton cancelBtn = new JButton("Cancel");
        loginBtn.addActionListener(new BtnListener());
        cancelBtn.addActionListener(new BtnListener());

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginBtn);
        panel.add(cancelBtn);

        this.add(panel);
        this.setTitle("Login");
        this.setResizable(false);
        this.setSize(300, 150);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getRootPane().setDefaultButton(loginBtn);
        this.setVisible(true);
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
        passwordArray = passwordField.getPassword();
        return new String(passwordArray);

    }

    /**
     * Class that implements the ActionListener interface and has the commands for the buttons
     *
     * @author suckowm
     */
    private class BtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Login")) {
                String error = "";
                try {
                    successfullLogin = DatabaseConnection.getInstance().ValidateUser(getUsername(), getPassword());
                } catch (IOException e1) {
                    successfullLogin = false;
                    error = "Error connecting to database";
                }

                if (successfullLogin) {
                    dispose();
                } else {
                    if (error.length() == 0) {
                        error = "Unable to login with given credentials";
                    }

                    JOptionPane.showMessageDialog(LoginUI.this, error);
                }

            } else {
                LoginUI.this.dispose();
            }

        }
    }
}
