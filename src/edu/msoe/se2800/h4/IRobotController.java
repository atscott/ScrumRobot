package edu.msoe.se2800.h4;

public interface IRobotController {
    
    public void driveForward();
    
    public void setVelocity(int velocity);
    
    public int getVelocity();
    
    public void stopRobotMotion();
    
    public void driveRobotBackward();
    
    public boolean isRunning();

}
