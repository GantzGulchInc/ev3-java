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
        
        int nativeSpeed = (int)( rps * motor.getCountPerRotation());

        if( Math.abs(nativeSpeed) > Math.abs( motor.getMaxSpeed()) ) {
            throw new IllegalArgumentException(String.format("Speed [%f dpm / %d] exceeds motor capacity: %d", dpm, nativeSpeed, motor.getMaxSpeed() ) );
        }
        
        return nativeSpeed;
    }

    @Override
    public String toString() {
        return String.format("%fdpm", dpm);
    }

}
