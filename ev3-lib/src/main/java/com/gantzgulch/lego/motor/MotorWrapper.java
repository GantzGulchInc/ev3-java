package com.gantzgulch.lego.motor;

import java.util.concurrent.TimeUnit;

import com.gantzgulch.lego.device.ev3.EV3Motor.EV3MotorCommand;
import com.gantzgulch.lego.device.ev3.EV3TachoMotor;
import com.gantzgulch.lego.motor.ev3.EV3TachoMotorWrapper;
import com.gantzgulch.lego.unit.Speed;
import com.gantzgulch.lego.units.LengthUnit;
import com.gantzgulch.lego.wheel.Wheel;

public interface MotorWrapper {

    void setSpeed(Speed speed);
    
    void setBrake(boolean useBrake);
    
    void onForTime(long timeUnit, TimeUnit unit, boolean wait);

    void onForRotations(double rotations, boolean wait);
    
    void onForDegrees(double degrees, boolean wait);
    
    void onForDistance(Wheel wheel, double lenthUnit, LengthUnit unit, boolean wait);
    
    void onForever();
    
    void stop();
    
    static MotorWrapper create(final EV3TachoMotor<EV3MotorCommand> tachMotor) {
        return new EV3TachoMotorWrapper(tachMotor);
    }
}
