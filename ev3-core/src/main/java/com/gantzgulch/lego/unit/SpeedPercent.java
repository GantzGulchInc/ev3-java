package com.gantzgulch.lego.unit;

import com.gantzgulch.lego.device.ev3.EV3Motor.EV3MotorCommand;
import com.gantzgulch.lego.device.ev3.EV3TachoMotor;

public class SpeedPercent implements Speed {

    private final double percent;

    public SpeedPercent(final double percent) {
        this.percent = percent;
    }

    @Override
    public int toNative(final EV3TachoMotor<EV3MotorCommand> motor) {
        return (int)( percent / 100.0 * motor.getMaxSpeed() );
    }
    
    public double getPercent() {
        return percent;
    }

    @Override
    public String toString() {
        return String.format("%f%%", percent);
    }
}
