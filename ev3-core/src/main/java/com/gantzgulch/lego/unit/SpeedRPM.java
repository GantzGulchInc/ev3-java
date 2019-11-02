package com.gantzgulch.lego.unit;

import com.gantzgulch.lego.device.ev3.EV3Motor.EV3MotorCommand;
import com.gantzgulch.lego.device.ev3.EV3TachoMotor;

public class SpeedRPM implements Speed {

    final double rpm;
    
    public SpeedRPM(final double rotationsPerMinute) {
        this.rpm = rotationsPerMinute;
    }


    @Override
    public int toNative(final EV3TachoMotor<EV3MotorCommand> motor) {
        
        double rps = rpm / 60.0;
        
        return (int)( rps * motor.getCountPerRotation());
    }

    public double getRpm() {
        return rpm;
    }
    
    @Override
    public String toString() {
        return String.format("%frpm", rpm);
    }
}
