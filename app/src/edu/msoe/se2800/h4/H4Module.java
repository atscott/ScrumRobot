
package edu.msoe.se2800.h4;

import dagger.Module;
import dagger.Provides;
import edu.msoe.se2800.h4.control.BatteryH4;
import edu.msoe.se2800.h4.control.IBattery;
import edu.msoe.se2800.h4.jplot.JPlotController;
import edu.msoe.se2800.h4.jplot.Main;

import javax.inject.Singleton;

/**
 * Dagger injection module used for offline testing and development. All implementations do not rely
 * on the robot.
 * 
 * @author marius
 */
@Module(entryPoints = Main.class, staticInjections = StatsTimerDaemon.class)
public class H4Module {

    @Provides
    public IBattery provideBattery() {
        return new BatteryH4();
    }

    @Provides
    @Singleton
    public JPlotController provideJPlotController(IRobotController controller) {
        return new JPlotController();
    }

    @Provides
    public IRobotController providesIRobotController() {
        return new RobotControllerH4();
    }

}
