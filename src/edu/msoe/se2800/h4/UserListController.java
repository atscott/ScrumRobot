package edu.msoe.se2800.h4;

import edu.msoe.se2800.h4.AdministrationFeatures.DatabaseConnection;

import java.io.IOException;
import java.util.List;

import javax.swing.DefaultListModel;

public class UserListController {
	
	private UserListUI ui;
	
	public UserListController() {
        try {
            ui = new UserListUI(
                    DatabaseConnection.getInstance().getUsernamesWithRole(DatabaseConnection.UserTypes.OBSERVER),
                    DatabaseConnection.getInstance().getUsernamesWithRole(DatabaseConnection.UserTypes.PROGRAMMER),
                    DatabaseConnection.getInstance().getUsernamesWithRole(DatabaseConnection.UserTypes.ADMIN),
                    this);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

	public void onClose(DefaultListModel observerModel, DefaultListModel programmerModel, DefaultListModel administratorModel) {
		//TODO update all users here
		//TODO go through each element in model and make sure they are set to observers in the database
		
		//TODO go through each element in model and make sure they are set to observers in the database
		
		//TODO go through each element in model and make sure they are set to observers in the database
		
		
	}

}