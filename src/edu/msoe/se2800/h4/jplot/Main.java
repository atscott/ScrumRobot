package edu.msoe.se2800.h4.jplot;


import edu.msoe.se2800.h4.AdministrationFeatures.DatabaseConnection;
import edu.msoe.se2800.h4.AdministrationFeatures.LoginUI;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        LoginUI login = new LoginUI();
        if (login.wasLoginSuccessful()) {
            JPlotController controller = JPlotController.getInstance();
            controller.init();
            try {
                controller.changeMode(DatabaseConnection.getInstance().getUserRole(login.getUsername()));
            } catch (IOException e) {
                System.out.println("Unable to retrieve user role and set grid mode");
            }
        }
    }
}
