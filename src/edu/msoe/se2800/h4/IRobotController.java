package edu.msoe.se2800.h4;

import lejos.robotics.navigation.Waypoint;

public interface IRobotController {
    
    public void setPath();
    
    public void setVelocity(double velocity);
    
    public double getVelocity();
    
    public void travelPath();
        
    public boolean isRunning();

    public void goToWaypoint(Waypoint wp);//Single step
    
    public void setReverse();
    
    public void stop();
}
