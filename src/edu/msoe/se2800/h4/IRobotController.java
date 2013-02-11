package edu.msoe.se2800.h4;

import lejos.robotics.navigation.Waypoint;
import lejos.robotics.pathfinding.Path;

public interface IRobotController {

    /**
     * sets the path of the robot
     * @param aPath
     */
    public void setPath(Path aPath);

    /**
     * adds waypoint to the end of the current path
     * @param aWaypoint
     */
    public void addWaypoint(Waypoint aWaypoint);

    /**
     * tells the robot to follow the route. If single step is true then it will move a single step
     */
    public void followRoute();

    /**
     * tells the robot to go to the waypoint immediately
     * @param destination
     */
    public void goToImmediate(Waypoint destination);

    /**
     * tells the robot to stop after finishing its current move
     */
    public void stop();

    /**
     * tells the robot to stop immediately
     */
    public void stopImmediate();

    /**
     * @return if the robot is moving or not
     */
    public boolean isMoving();

    /**
     * @param setting true if you want followRoute to do a single step
     */
    public void singleStep(boolean setting);

    /**
     * @return The current location of the robot
     */
    public Waypoint getLocation();


    /**
     * @param velocity The velocity for the robot
     */
    public void setVelocity(double velocity);

    /**
     * @return the current velocity of the robot
     */
    public double getVelocity();

    /**
     * @param isReverse True if you want to robot to navigate path in reverse
     */
    public void setReverse(boolean isReverse);
    
}
