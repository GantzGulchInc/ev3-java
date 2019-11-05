/*******************************************************************************
 *    Copyright 2019 GantzGulch, Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *******************************************************************************/
package com.gantzgulch.lego.api.device.ev3;

import java.time.Duration;
import java.util.Set;

import com.gantzgulch.lego.api.unit.Speed;

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
    
    void setSpeedSetPoint(Speed speed);

    Duration getRampUpSetPoint();

    void setRampUpSetPoint(Duration duration);

    Duration getRampDownSetPoint();

    void setRampDownSetPoint(Duration duration);

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

    Duration getTimeSetPoint();

    void setTimeSetPoint(Duration duration);

    boolean isRunning();
}
