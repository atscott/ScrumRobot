
package edu.msoe.se2800.h4.control;

import lejos.nxt.Battery;

public class BatteryLejos implements IBattery {

    @Override
    public int getVoltageMilliVolt() {
        return Battery.getVoltageMilliVolt();
    }
    
    

}
