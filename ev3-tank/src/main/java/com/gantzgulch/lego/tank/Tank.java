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
    
    void onForDuration(Speed leftSpeed, Speed rightSpeed, Duration duration, boolean brake, boolean wait);
    
    void steerForRotations(int steering, Speed speed, double rotations, boolean brake, boolean wait);
    
    public static Tank create(EV3TachoMotor<EV3MotorCommand> leftMotor, EV3TachoMotor<EV3MotorCommand> rightMotor, Wheel wheel) {
        return new EV3TachoTank(leftMotor, rightMotor, wheel);
    }
}
