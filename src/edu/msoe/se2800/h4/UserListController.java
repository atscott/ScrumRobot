package edu.msoe.se2800.h4;

public class UserListController {
	
	public static void main(String[] args) {
		new UserListController();
	}
	
	private UserListUI ui;
	
	public UserListController() {
		UserListUI ui = new UserListUI(this);
	}

}
