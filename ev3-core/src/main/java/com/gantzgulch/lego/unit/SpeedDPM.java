package com.gantzgulch.lego.unit;

import com.gantzgulch.lego.device.ev3.EV3Motor.EV3MotorCommand;
import com.gantzgulch.lego.device.ev3.EV3TachoMotor;

public class SpeedDPM implements Speed {

    private final double dpm;

    public SpeedDPM(final double degreesPerMinute) {
        this.dpm = degreesPerMinute;
    }

    @Override
    public int toNative(final EV3TachoMotor<EV3MotorCommand> motor) {
        
        double rpm = dpm / 360.0;
        
        double rps = rpm / 60.0;
        
        return (int)( rps * motor.getCountPerRotation());
    }

    @Override
    public String toString() {
        return String.format("%fdpm", dpm);
    }

}
