
package edu.msoe.se2800.h4;

import edu.msoe.se2800.h4.control.IBattery;
import edu.msoe.se2800.h4.jplot.JPlotController;

import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

public class StatsTimerDaemon {
    
    private static final double sMetersPerInch = .0254;
    private static final int sMaxBattery = 7400;
    
    @Inject
    static IBattery sBattery;
    
    @Inject
    static IRobotController sRobotController;
    
    public static void start() {
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                StatsUpdateEvent event = new StatsUpdateEvent();
                event.milliVoltsPercent = sBattery.getVoltageMilliVolt() / sMaxBattery;
                double velocity = sRobotController.getVelocity();
                velocity = velocity * sMetersPerInch / 60;
                event.velocity = velocity;
                JPlotController.getInstance().getEventBus().post(event);
            }

        }, 0, 1000);
    }

}
