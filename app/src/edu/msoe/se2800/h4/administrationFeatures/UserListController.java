package edu.msoe.se2800.h4.administrationFeatures;

import edu.msoe.se2800.h4.administrationFeatures.DatabaseConnection;

import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class UserListController {
	
	public UserListController() {
        try {
            new UserListUI(
                    DatabaseConnection.getInstance().getUsernamesWithRole(DatabaseConnection.UserTypes.OBSERVER),
                    DatabaseConnection.getInstance().getUsernamesWithRole(DatabaseConnection.UserTypes.PROGRAMMER),
                    DatabaseConnection.getInstance().getUsernamesWithRole(DatabaseConnection.UserTypes.ADMIN),
                    this);
        } catch (IOException e) {
            System.out.println("Could not retrieve a list of users");
        }
    }

	/**
	 * Method that handles when the User List is closed
	 * @param observerModel DefaultListModel
	 * @param programmerModel DefaultListModel
	 * @param administratorModel DefaultListModel
	 */
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
	
	/**
	 * Creates a change password UI
	 * @param username String username
	 */
	public void showChangePassword(String username) {
		new PasswordChangeUI(this, username);
	}
	
	/**
	 * Called when the SAVE buttons is click on the UI
	 * @param username String username
	 * @param password String password
	 * @return Boolean
	 */
	public boolean onPasswordChangeSave(String username, String password) {
		boolean success = false;
		try {
			ResultInfo ri = DatabaseConnection.getInstance().changeUserInfo(username, password, DatabaseConnection.getInstance().getUserRole(username));
			if (!ri.wasSuccess()) {
				JOptionPane.showMessageDialog(null, ri.getMessage());
			} else {
				success = true;
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return success;
	}
	
	/**
	 * Called when a user is selected and the backspace / delete button is pushed
	 * @param username to delete
	 * @return true if the user was deleted otherwise false
	 */
	public boolean deleteUser(String username) {
		boolean success = false;
		try {
			DatabaseConnection.getInstance().deleteUser(username);
			success = true;
		} catch (IllegalArgumentException e) {
			System.out.println("Could not delete the user with name "+username);
		} catch (IOException e) {
			System.out.println("Could not delete the user with name "+username);
		}
		return success;
	}

}
