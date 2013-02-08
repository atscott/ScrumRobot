
package edu.msoe.se2800.h4;

import lejos.nxt.Motor;
import lejos.nxt.remote.RemoteMotor;
import lejos.robotics.navigation.Waypoint;
import lejos.robotics.pathfinding.Path;

public class RobotControllerLejos implements IRobotController {


    @Override
    public void setPath(Path aPath) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addWaypoint(Waypoint aWaypoint) {
        //To change body of implemented methods use File | Settings | File Templates.
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
    public double getOrientation() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
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
