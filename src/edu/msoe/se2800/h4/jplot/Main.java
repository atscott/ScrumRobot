package edu.msoe.se2800.h4.jplot;


import edu.msoe.se2800.h4.RobotController;

public class Main {


    public static void main(String[] args) {
    	System.setProperty("apple.laf.useScreenMenuBar", "true");
    	System.setProperty("com.apple.mrj.application.apple.menu.about.name", ".Scrumbot");
        RobotController rc = new RobotController();
        JPlotController.getInstance().start(rc);
    }


}
