package edu.msoe.se2800.h4;

/**
 * POJO to hold status updates
 * @author marius
 *
 */
public class StatsUpdateEvent {
    
    /**
     * How much battery is remaining as a percent
     */
    private int mMilliVoltsPercent;
    
    /**
     * Velocity of the robot in meters per second
     */
    private double mVelocity;
    
    public StatsUpdateEvent(int milliVoltsPercent, double velocity) {
        mMilliVoltsPercent = milliVoltsPercent;
        mVelocity = velocity;
    }

    public int getmilliVoltsPercent() {
        return mMilliVoltsPercent;
    }

    public double getVelocity() {
        return mVelocity;
    }

}
