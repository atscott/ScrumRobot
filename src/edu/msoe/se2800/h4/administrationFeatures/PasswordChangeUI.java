package edu.msoe.se2800.h4.administrationFeatures;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PasswordChangeUI extends JDialog {

	/** Generated serialVersionUID */
	private static final long serialVersionUID = -4811400671380444436L;
	
	private UserListController controller;
	private String username;
	private JTextField txtPassword, txtPasswordRetype;
	
	public PasswordChangeUI(UserListController controller, String username) {
		setModal(true);
		this.controller = controller;
		this.username = username;
		initSubviews();
	}
	
	private void initSubviews() {
		JPanel panel = new JPanel();
		
		JLabel usernameLabel = new JLabel("Username: ");
        JLabel passwordLabel = new JLabel("Password: ");
        JLabel passwordRetypeLabel = new JLabel("Retype Password: ");
        
        JTextField txtUsername = new JTextField(15);
		txtUsername.setText(username);
		txtUsername.setEditable(false);
		
        txtPassword = new JPasswordField(15);
        txtPasswordRetype = new JPasswordField(15);

        JButton saveBtn = new JButton("Save");
        JButton cancelBtn = new JButton("Cancel");
        saveBtn.setActionCommand("save");
        cancelBtn.setActionCommand("cancel");
        saveBtn.addActionListener(new BtnListener());
        cancelBtn.addActionListener(new BtnListener());

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        panel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        panel.add(txtUsername, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        panel.add(txtPassword, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(passwordRetypeLabel, gbc);
        gbc.gridx = 1;
        panel.add(txtPasswordRetype, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(saveBtn, gbc);
        gbc.gridx = 1;
        panel.add(cancelBtn, gbc);

        this.add(panel);
        this.setTitle("Change Password");
        this.setResizable(false);
        this.setSize(325, 175);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        getRootPane().setDefaultButton(saveBtn);
        this.setVisible(true);
	}
	
	private class BtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equalsIgnoreCase("save")) {
                if (controller.onPasswordChangeSave(username, txtPassword.getText())) {
                	dispose();
                }
            } else if (e.getActionCommand().equalsIgnoreCase("cancel")) {
                dispose();
            }

        }
    }

}