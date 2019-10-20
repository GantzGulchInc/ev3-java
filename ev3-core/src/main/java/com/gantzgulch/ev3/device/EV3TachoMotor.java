package com.gantzgulch.ev3.device;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface EV3TachoMotor<C extends Enum<?>> extends Motor<C> {

    Set<EV3MotorStopAction> getStopActions();
    
    void setStopAction(EV3MotorStopAction stopAction);

    int getMaxSpeed();
    
    void setSpeedSetPoint(int speed);
    
    void setRampUpSetPoint(long timeUnit, TimeUnit unit);
    
    void setRampDownSetPoint(long timeUnit, TimeUnit unit);
    
    void setTimeSetPoint(long timeUnit, TimeUnit unit);
    
    Set<EV3MotorState> getState();
    
    int getPosition();
}
