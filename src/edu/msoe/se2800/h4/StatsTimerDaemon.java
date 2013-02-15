
package edu.msoe.se2800.h4;

import com.google.common.eventbus.EventBus;

import edu.msoe.se2800.h4.control.IBattery;
import edu.msoe.se2800.h4.jplot.JPlotController;

import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

/**
 * Provides a service to retrieve system data from the Robot.
 * 
 * @author marius
 */
public class StatsTimerDaemon {

    private static final double sMetersPerInch = .0254;
    private static final float sMaxBattery = 12000;

    @Inject
    static IBattery sBattery;

    @Inject
    static IRobotController sRobotController;

    /**
     * Starts the retrieval service. This will run in a separate thread and will submit the system
     * data as {@link StatsUpdateEvent}s to the underlying {@link EventBus}
     */
    public static void start() {

        // Start the timer as a daemon to prevent it from keeping the system awake
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {

                // The battery sometimes reports higher voltages than it is rated for. Standardize
                // to a maximum value.
                float milliVolts = Math.min(sMaxBattery, sBattery.getVoltageMilliVolt());

                // Convert the battery amount to a percentage
                int percent = Math.round((milliVolts / sMaxBattery) * 100);

                // Calculate the velocity in meters per second
                double velocity = sRobotController.getVelocity();
                velocity = velocity * sMetersPerInch / 60;
                StatsUpdateEvent event = new StatsUpdateEvent(percent, velocity);
                JPlotController.getInstance().getEventBus().post(event);
            }

        }, 0, 1000); // Schedule for every second
    }

}
