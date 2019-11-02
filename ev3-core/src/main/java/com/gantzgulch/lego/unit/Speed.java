package com.gantzgulch.lego.unit;

import com.gantzgulch.lego.device.ev3.EV3Motor.EV3MotorCommand;
import com.gantzgulch.lego.device.ev3.EV3TachoMotor;

public interface Speed {

    int toNative(EV3TachoMotor<EV3MotorCommand> motor);
    
    // double rotationsPerSecond(double maxRotationsPerSecond);
    
    public static Speed parse(final String value) {
        return SpeedParser.parse(value).orElseThrow( () -> new IllegalArgumentException("Unable to parse speed: " + value) );
    }
}
