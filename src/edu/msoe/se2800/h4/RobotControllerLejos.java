
package edu.msoe.se2800.h4;

import java.awt.Point;

import lejos.nxt.Motor;
import lejos.nxt.remote.RemoteMotor;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Pose;
import lejos.robotics.navigation.Waypoint;
import lejos.robotics.pathfinding.Path;

/**
 * Provides the basic functions for controlling the lejos robot.
 *
 * @author koenigj
 */
public class RobotControllerLejos implements IRobotController {

    /**
     * The pilot class is set to 2 cm wheel diameter and 7 cm between the wheels
     */
    private DifferentialPilot pilot;
    /**
     * The Navigator is used to follow a path or by point.
     */
    private Navigator nav;
    /**
     * The path to be followed by the robot.
     */
    private Path path;

    private boolean stopRequested = false;
    private boolean singleStep;
    private boolean isReverse = false;
    private boolean returningToBeginning = false;

    /**
     * The constructor instantiates the pilot and navigator. Single step is false.
     */
    public RobotControllerLejos() {
        path = new Path();
        pilot = new DifferentialPilot(2, 7, Motor.A, Motor.B);
        nav = new Navigator(pilot);
        this.singleStep = false;
        nav.singleStep(false);
    }

    /**
     * Set the path to be followed.
     */
    @Override
    public void setPath(Path path) {
        this.path = path;
        nav.setPath(path);
    }

    public void followRoute() {
        this.stopRequested = false;

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                if (!stopRequested) {
                    if (nav.pathCompleted()) {
                        //toggle the state of the return
                        returningToBeginning = !returningToBeginning;

                        //if we are returning to beginning of the path and isReverse is true, then
                        //navigation should procede in a forward manner
                        if (returningToBeginning && isReverse) {
                            pilot = new DifferentialPilot(2, 7, Motor.A, Motor.B, false);

                        } else if (returningToBeginning && !isReverse) {
                            pilot = new DifferentialPilot(2, 7, Motor.A, Motor.B, true);
                        } else {
                            //if we are not returning to the beginning, then navigation should proceed
                            //in the manner defined by the reverse option
                            pilot = new DifferentialPilot(2, 7, Motor.A, Motor.B, isReverse);
                        }
                        nav = new Navigator(pilot);
                    }
                    nav.followPath();
                    nav.waitForStop();
                    // This has to be reset since path is reset in navigator after the followPath is called
                    if (nav.pathCompleted()) {
                        for (int i = 0; i < path.size(); i++) {
                            nav.addWaypoint(path.get(i));
                        }
                    }

                    //only loop if this is not single step mode
                    if (!RobotControllerLejos.this.singleStep) {
                        this.run();
                    }
                }
            }
        });
        t.start();
    }

//    /**
//     * Follow the route
//     */
//    @Override
//    public void followRoute() {
//        this.stopRequested = false;
//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                if (!stopRequested) {
//                    pilot = new DifferentialPilot(2, 7, Motor.A, Motor.B, false);
//                    nav = new Navigator(pilot);
//
//                    forward();
//                    reverse();
//                    nav.waitForStop();
//                    // This has to be reset since path is reset in navigator after the followPath is called
//                    for (int i = 0; i < path.size(); i++) {
//                        nav.addWaypoint(path.get(i));
//                    }
//                    System.out.println(nav.getPath().size());
//                    System.out.println(path.size());
//
//                    //only loop if this is not single step mode
//                    if (!RobotControllerLejos.this.singleStep) {
//                        this.run();
//                    }
//                }
//            }
//        });
//        t.start();
//    }

    @Override
    public void addWaypoint(Waypoint wp) {
        nav.addWaypoint(wp);
        path.add(wp);
    }

    @Override
    public Path getPath() {
        return path;
    }

    @Override
    public void goToImmediate(Waypoint wp) {
        if (path != null) {
            path.clear();
            nav.clearPath();
        }
        nav.goTo(wp);
        nav.waitForStop();
        path.clear();
        nav.clearPath();
    }

    @Override
    public void singleStep(boolean setting) {
        nav.singleStep(setting);
        this.singleStep = setting;
    }

    @Override
    public Waypoint getLocation() {
        PoseProvider provider = nav.getPoseProvider();
        Pose pose = provider.getPose();
        return (Waypoint) pose.getLocation();

    }

    @Override
    public void setVelocity(double speed) {
        pilot.setTravelSpeed(speed);
    }

    @Override
    public boolean isMoving() {
        return pilot.isMoving();
    }

    @Override
    public void setReverse(boolean isReverse) {
        this.isReverse = isReverse;
        pilot = new DifferentialPilot(2, 7, Motor.A, Motor.B, isReverse);
        nav = new Navigator(pilot);
    }


    @Override
    public void stop() {
        stopRequested = true;
        nav.singleStep(true);
    }

    @Override
    public void stopImmediate() {
        stopRequested = true;
        nav.stop();
    }

    @Override
    public double getVelocity() {
        return pilot.getTravelSpeed();
    }
}
