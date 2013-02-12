package edu.msoe.se2800.h4;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.msoe.se2800.h4.control.BatteryLejos;
import edu.msoe.se2800.h4.control.IBattery;
import edu.msoe.se2800.h4.jplot.JPlotController;
import edu.msoe.se2800.h4.jplot.Main;

@Module(entryPoints = Main.class, staticInjections = StatsTimerDaemon.class)
public class LejosModule {
    
    @Provides
    IBattery provideBattery() {
        return new BatteryLejos();
    }
    
    @Provides
    @Singleton
    public JPlotController provideJPlotController(IRobotController controller) {
        return new JPlotController();
    }
    
    @Provides
    public IRobotController providesIRobotController() {
        return new RobotControllerLejos();
    }
}
