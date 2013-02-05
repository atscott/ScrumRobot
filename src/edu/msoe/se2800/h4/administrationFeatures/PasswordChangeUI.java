package edu.msoe.se2800.h4.administrationFeatures;

public class PasswordChangeUI extends LoginUI {

	/** Generated serialVersionUID */
	private static final long serialVersionUID = -4811400671380444436L;
	
	private UserListController controller;
	private String username;
	
	public PasswordChangeUI(UserListController controller, String username) {
		super();
		this.controller = controller;
		this.username = username;
		setModal(true);
		
		usernameField.setText(username);
		usernameField.setEditable(false);
		loginBtn.setText("Change Password");
		this.invalidate();
	}

}
