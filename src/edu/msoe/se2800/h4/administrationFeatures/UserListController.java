package edu.msoe.se2800.h4.administrationFeatures;

import edu.msoe.se2800.h4.administrationFeatures.DatabaseConnection;

import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class UserListController {
	
	private UserListUI ui;
	private PasswordChangeUI passChange;
	
	public UserListController() {
        try {
            ui = new UserListUI(
                    DatabaseConnection.getInstance().getUsernamesWithRole(DatabaseConnection.UserTypes.OBSERVER),
                    DatabaseConnection.getInstance().getUsernamesWithRole(DatabaseConnection.UserTypes.PROGRAMMER),
                    DatabaseConnection.getInstance().getUsernamesWithRole(DatabaseConnection.UserTypes.ADMIN),
                    this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public void onClose(DefaultListModel observerModel, DefaultListModel programmerModel, DefaultListModel administratorModel) {
		boolean errors = false;
		for (int i = 0; i < observerModel.size(); i++) {
			try {
				DatabaseConnection.getInstance().changeUserInfo(observerModel.get(i).toString(), DatabaseConnection.getInstance().getUserPassword(observerModel.get(i).toString()), DatabaseConnection.UserTypes.OBSERVER);
			} catch (IllegalArgumentException e) {
				errors = true;
				e.printStackTrace();
			} catch (IOException e) {
				errors = true;
				e.printStackTrace();
			}
		}
		for (int i = 0; i < programmerModel.size(); i++) {
			try {
				DatabaseConnection.getInstance().changeUserInfo(programmerModel.get(i).toString(), DatabaseConnection.getInstance().getUserPassword(programmerModel.get(i).toString()), DatabaseConnection.UserTypes.PROGRAMMER);
			} catch (IllegalArgumentException e) {
				errors = true;
				e.printStackTrace();
			} catch (IOException e) {
				errors = true;
				e.printStackTrace();
			}
		}
		for (int i = 0; i < administratorModel.size(); i++) {
			try {
				DatabaseConnection.getInstance().changeUserInfo(administratorModel.get(i).toString(), DatabaseConnection.getInstance().getUserPassword(administratorModel.get(i).toString()), DatabaseConnection.UserTypes.ADMIN);
			} catch (IllegalArgumentException e) {
				errors = true;
				e.printStackTrace();
			} catch (IOException e) {
				errors = true;
				e.printStackTrace();
			}
		}
		
		if (errors) {
			JOptionPane.showMessageDialog(null, "An error occured editing the users.  Some of the changes may not persist.");
		}
	}
	
	public void showChangePassword(String username) {
		passChange = new PasswordChangeUI(this, username);
	}
	
	public void onPasswordChangeSave() {
		
	}

}
