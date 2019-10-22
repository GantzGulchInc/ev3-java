package com.gantzgulch.lego.device.ev3;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface EV3TachoMotor<CMDS extends Enum<?>> extends EV3Motor<CMDS> {

    int getCountPerRotation();

    int getDutyCycle();

    int getDutyCycleSetPoint();

    void setDutyCycleSetPoint(int newDutyCycleSetPoint);

    EV3MotorPolarity getPolarity();

    void setPolarity(EV3MotorPolarity newPolarity);

    int getPosition();

    void setPosition(int newPosition);

    int getHoldPidKd();

    void setHoldPidKd(int newHoldPidKd);

    int getHoldPidKi();

    void setHoldPidKi(int newHoldPidKi);

    int getHoldPidKp();

    void setHoldPidKp(int newHoldPidKp);

    int getMaxSpeed();

    int getPositionSetPoint();

    void setPositionSetPoint(int newPositionSetPoint);

    int getSpeed();

    int getSpeedSetPoint();

    void setSpeedSetPoint(int speed);

    int getRampUpSetPointMillis();

    void setRampUpSetPoint(long timeUnit, TimeUnit unit);

    int getRampDownSetPointMillis();

    void setRampDownSetPoint(long timeUnit, TimeUnit unit);

    int getSpeedPidKd();

    void setSpeedPidKd(int newSpeedPidKd);

    int getSpeedPidKi();

    void setSpeedPidKi(int newSpeedPidKi);

    int getSpeedPidKp();

    void setSpeedPidKp(int newSpeedPidKp);

    Set<EV3MotorState> getState();

    EV3MotorStopAction getStopAction();

    void setStopAction(EV3MotorStopAction stopAction);

    Set<EV3MotorStopAction> getStopActions();

    int getTimeSetPointMillis();

    void setTimeSetPoint(long timeUnit, TimeUnit unit);

}
