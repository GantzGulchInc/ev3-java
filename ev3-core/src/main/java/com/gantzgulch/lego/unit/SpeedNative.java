package com.gantzgulch.lego.unit;

import com.gantzgulch.lego.device.ev3.EV3Motor.EV3MotorCommand;
import com.gantzgulch.lego.device.ev3.EV3TachoMotor;

public class SpeedNative implements Speed {

    private final double nativeSpeed;

    public SpeedNative(final double nativeSpeed) {
        this.nativeSpeed = nativeSpeed;
    }

    @Override
    public int toNative(final EV3TachoMotor<EV3MotorCommand> motor) {
        return (int)nativeSpeed;
    }
    
    @Override
    public String toString() {
        return "Speed:" + Double.toString(nativeSpeed) + "%";
    }
}
