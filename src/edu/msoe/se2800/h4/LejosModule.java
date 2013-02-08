package edu.msoe.se2800.h4;

import dagger.Module;
import dagger.Provides;
import edu.msoe.se2800.h4.control.BatteryLejos;
import edu.msoe.se2800.h4.control.IBattery;
import edu.msoe.se2800.h4.jplot.Main;

@Module(entryPoints = Main.class)
public class LejosModule {
    
    @Provides
    IBattery provideBattery() {
        return new BatteryLejos();
    }
    

}
