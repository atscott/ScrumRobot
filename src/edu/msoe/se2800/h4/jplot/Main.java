
package edu.msoe.se2800.h4.jplot;

import dagger.ObjectGraph;
import edu.msoe.se2800.h4.H4Module;
import edu.msoe.se2800.h4.IRobotController;
import edu.msoe.se2800.h4.LejosModule;

import javax.inject.Inject;

public class Main implements Runnable {

    @Inject
    private JPlotController mJPlotController;

    @Inject
    private IRobotController mRobotController;

    @Override
    public void run() {
        mJPlotController.start(mRobotController);
    }

    public static void main(String[] args) {

        System.setProperty("apple.laf.useScreenMenuBar", "true");
        System.setProperty("com.apple.mrj.application.apple.menu.about.name", ".Scrumbot");

        // Setup dependency injection
        ObjectGraph objectGraph = ObjectGraph.create(new H4Module());
        objectGraph.injectStatics();
        Main main = objectGraph.get(Main.class);
        main.run();

        // RobotController rc = new RobotController();
        // JPlotController.getInstance().start(rc);
    }

}
