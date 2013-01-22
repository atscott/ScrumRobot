package edu.msoe.se2800.h4.jplot;


import edu.msoe.se2800.h4.AdministrationFeatures.LoginUI;

public class Main {

    public static void main(String[] args) {
        LoginUI login = new LoginUI();
        if (login.wasLoginSuccessful()) {
            JPlotController controller = JPlotController.getInstance();
            controller.init();
        }
    }

}
