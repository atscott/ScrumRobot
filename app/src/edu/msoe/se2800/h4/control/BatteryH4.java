package edu.msoe.se2800.h4.control;

import java.util.Random;

public class BatteryH4 implements IBattery {

    @Override
    public int getVoltageMilliVolt() {
        Random random = new Random();
        return random.nextInt(11999) + 1;
    }
    
    

}
