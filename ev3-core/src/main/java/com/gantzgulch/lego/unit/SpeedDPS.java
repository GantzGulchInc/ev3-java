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
        
        int nativeSpeed = (int)( rps * motor.getCountPerRotation() );
        
        if( Math.abs(nativeSpeed) > Math.abs( motor.getMaxSpeed()) ) {
            throw new IllegalArgumentException(String.format("Speed [%f dps / %d] exceeds motor capacity: %d", dps, nativeSpeed, motor.getMaxSpeed() ) );
        }
        
        return nativeSpeed;
    }
    
    @Override
    public String toString() {
        return String.format("%fdps", dps);
    }
}
