
package edu.msoe.se2800.h4;

import lejos.robotics.navigation.Waypoint;
import lejos.robotics.pathfinding.Path;

/**
 * Offline implementation of the RobotController. Does not interact with the robot.
 *
 * @author scotta
 */
public class RobotControllerH4 implements IRobotController {

    Path p = new Path();
    private boolean stopRequested = false;

    @Override
    public void setPath(Path aPath) {
        p = aPath;
    }

    @Override
    public void addWaypoint(Waypoint aWaypoint) {
        p.add(aWaypoint);
    }

    @Override
    public Path getPath() {
        return p; // To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void followRoute() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                if (!stopRequested) {
                    for (int i = 0; i < 1000000; i++) {
                        System.out.println("going" + i);
                    }
                    this.run();
                }
            }

        });

        // start adding in another thread
        t.start();
    }

    @Override
    public void goToImmediate(Waypoint destination) {
        // To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void stop() {
        stopRequested = true;
    }

    @Override
    public void stopImmediate() {
        stopRequested = true;
    }

    @Override
    public boolean isMoving() {
        return false; // To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void singleStep(boolean setting) {
        // To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Waypoint getLocation() {
        return null; // To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setVelocity(double velocity) {
        // To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public double getVelocity() {
        return 0; // To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setReverse(boolean isReverse) {
        // To change body of implemented methods use File | Settings | File Templates.
    }
}
