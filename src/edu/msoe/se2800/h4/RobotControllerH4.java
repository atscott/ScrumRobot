
package edu.msoe.se2800.h4;

import lejos.robotics.navigation.Waypoint;
import lejos.robotics.pathfinding.Path;

/**
 * Offline implementation of the RobotController
 * @author marius
 *
 */
public class RobotControllerH4 implements IRobotController {
	
	Path p = new Path();


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
        return p;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void followRoute() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void goToImmediate(Waypoint destination) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void stop() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void stopImmediate() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isMoving() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void singleStep(boolean setting) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Waypoint getLocation() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setVelocity(double velocity) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public double getVelocity() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setReverse(boolean isReverse) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
