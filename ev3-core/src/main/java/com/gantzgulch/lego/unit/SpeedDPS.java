package com.gantzgulch.lego.unit;

import com.gantzgulch.lego.device.ev3.EV3Motor.EV3MotorCommand;
import com.gantzgulch.lego.device.ev3.EV3TachoMotor;

public class SpeedDPS implements Speed {

    private final double dps;
    
    public SpeedDPS(final double degreesPerSecond) {
        this.dps = degreesPerSecond;
    }

    @Override
    public int toNative(final EV3TachoMotor<EV3MotorCommand> motor) {
        
        double rps = dps / 360.0;
                
        return (int)( rps * motor.getCountPerRotation() );
    }
    
    @Override
    public String toString() {
        return "Speed:" + Double.toString(dps) + "dps";
    }
}
