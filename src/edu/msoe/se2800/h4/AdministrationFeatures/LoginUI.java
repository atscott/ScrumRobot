
package edu.msoe.se2800.h4.AdministrationFeatures;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginUI {

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

    /**
     * Char array of the password
     */
    char[] passwordArray;

    /**
     * @param args
     */
    public static void main(String[] args) {
        LoginUI ui = new LoginUI();

    }

    /**
     * Constructor that initializes the UI
     */
    public LoginUI() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JLabel usernameLabel = new JLabel("Username: ");
        JLabel passwordLabel = new JLabel("Password: ");
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);

        JButton loginBtn = new JButton("Login");
        JButton cancelBtn = new JButton("Cancel");
        loginBtn.addActionListener(new BtnListener());
        cancelBtn.addActionListener(new BtnListener());

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginBtn);
        panel.add(cancelBtn);

        frame.add(panel);
        frame.setTitle("Login");
        frame.setResizable(false);
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    /**
     * Method that returns the username
     * 
     * @return String username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Method that returns the password
     * 
     * @return String password
     */
    public String getPassword() {
        return password;
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
                username = usernameField.getText();
                passwordArray = passwordField.getPassword();
                password = new String(passwordArray);
                // TODO Some sort of login feature
            } else {
                // TODO Should we kill the program is cancel is selected or just go to the version
                // where you can't do anything but watch?
            }

        }
    }
}
