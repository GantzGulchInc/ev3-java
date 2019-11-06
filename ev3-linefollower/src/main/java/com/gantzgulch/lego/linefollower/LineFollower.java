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
package com.gantzgulch.lego.linefollower;

import com.gantzgulch.lego.api.device.ev3.EV3ColorSensor;
import com.gantzgulch.lego.api.device.ev3.EV3MotorCommand;
import com.gantzgulch.lego.api.device.ev3.EV3TachoMotor;
import com.gantzgulch.lego.api.unit.Length;
import com.gantzgulch.lego.api.wheel.Wheel;
import com.gantzgulch.lego.linefollower.impl.LineFollowerImpl;

public interface LineFollower {

    void followLine(LineFollowerConfiguration config, Length length);
    
    public static LineFollower create(//
            final EV3TachoMotor<EV3MotorCommand> leftMotor, //
            final EV3TachoMotor<EV3MotorCommand> rightMotor, //
            final Wheel wheel, //
            final EV3ColorSensor colorSensor) {
        
        return new LineFollowerImpl(leftMotor, rightMotor, wheel, colorSensor);
    }
   
}
