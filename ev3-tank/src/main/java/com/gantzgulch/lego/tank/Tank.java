package com.gantzgulch.lego.tank;

import java.time.Duration;

import com.gantzgulch.lego.device.ev3.EV3Motor.EV3MotorCommand;
import com.gantzgulch.lego.device.ev3.EV3TachoMotor;
import com.gantzgulch.lego.tank.impl.EV3TachoTank;
import com.gantzgulch.lego.unit.Speed;
import com.gantzgulch.lego.wheel.Wheel;

public interface Tank {

    void setRampUp(Duration duration);
    
    void setRampDown(Duration duration);
    
    void onForDegrees(Speed leftSpeed, Speed rightSpeed, double degrees, boolean brake, boolean wait);
    
    void onForRotations(Speed leftSpeed, Speed rightSpeed, double rotations, boolean brake, boolean wait);
    
    public static Tank create(EV3TachoMotor<EV3MotorCommand> leftMotor, EV3TachoMotor<EV3MotorCommand> rightMotor, Wheel wheel) {
        return new EV3TachoTank(leftMotor, rightMotor, wheel);
    }
}
