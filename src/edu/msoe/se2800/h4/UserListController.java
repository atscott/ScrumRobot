package edu.msoe.se2800.h4;

import java.util.List;

import javax.swing.DefaultListModel;

public class UserListController {
	
	private UserListUI ui;
	
	public UserListController() {
		ui = new UserListUI(getMembersWithRole("observer"), getMembersWithRole("programmer"), getMembersWithRole("administrator"), this);
	}
	
	//role parameter is either observer, programmer, or administrator
	//can be changed to whatever the DB has the columns set to
	public List<String> getMembersWithRole(String role) {
		//TODO fetch all users and return their usernames in a List with the given role
		
		return null;
	}
	
	public void onClose() {
		//TODO update all users here
		DefaultListModel observerModel = ui.getObserversListModel();
		//TODO go through each element in model and make sure they are set to observers in the database
		
		DefaultListModel programmerModel = ui.getObserversListModel();
		//TODO go through each element in model and make sure they are set to observers in the database
		
		DefaultListModel administratorModel = ui.getObserversListModel();
		//TODO go through each element in model and make sure they are set to observers in the database
		
	}

}
