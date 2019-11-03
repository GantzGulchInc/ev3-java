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
        
        int nativeSpeed = (int)(rps * motor.getCountPerRotation());
        
        if( Math.abs(nativeSpeed) > Math.abs( motor.getMaxSpeed()) ) {
            throw new IllegalArgumentException(String.format("Speed [%f rps / %d] exceeds motor capacity: %d", rps, nativeSpeed, motor.getMaxSpeed() ) );
        }
        
        return nativeSpeed;
    }
    
    public double getRps() {
        return rps;
    }

    @Override
    public String toString() {
        return String.format("%frps", rps);
    }

}
