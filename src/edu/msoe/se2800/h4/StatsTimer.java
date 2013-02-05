
package edu.msoe.se2800.h4;

import edu.msoe.se2800.h4.jplot.JPlotController;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class StatsTimer {
    
    private static final double sMetersPerInch = .0254;

    private StatsTimer() {

    }

    public static void startTimerDaemon() {
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                StatsUpdateEvent event = new StatsUpdateEvent();
                Random random = new Random();
                event.milliVolts = random.nextInt();
                event.velocity = random.nextInt();
                // event.milliVolts = Battery.getVoltageMilliVolt();
                double velocity = JPlotController.getInstance().getRobotController().getVelocity();
                velocity = velocity * sMetersPerInch / RobotController.SECONDS_PER_MINUTE;
                //event.velocity = velocity;
                JPlotController.getInstance().getStatsEventBus().post(event);
            }

        }, 0, 1000);
    }

}
