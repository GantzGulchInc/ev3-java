package com.gantzgulch.lego.unit;

import com.gantzgulch.lego.device.ev3.EV3Motor.EV3MotorCommand;
import com.gantzgulch.lego.device.ev3.EV3TachoMotor;

public class SpeedRPS implements Speed {

    protected final double rps;

    public SpeedRPS(final double rotationsPerSecond) {
        this.rps = rotationsPerSecond;
    }

    @Override
    public int toNative(final EV3TachoMotor<EV3MotorCommand> motor) {
        
        return (int)(rps * motor.getCountPerRotation());
        
    }
    
    public double getRps() {
        return rps;
    }

    @Override
    public String toString() {
        return "Speed:" + Double.toString(rps) + "rps";
    }

}
