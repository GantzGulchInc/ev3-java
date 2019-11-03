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
package com.gantzgulch.lego.unit;

import com.gantzgulch.lego.device.ev3.EV3Motor.EV3MotorCommand;
import com.gantzgulch.lego.device.ev3.EV3TachoMotor;

public class SpeedRPM implements Speed {

    final double rpm;
    
    public SpeedRPM(final double rotationsPerMinute) {
        this.rpm = rotationsPerMinute;
    }


    @Override
    public int toNative(final EV3TachoMotor<EV3MotorCommand> motor) {
        
        double rps = rpm / 60.0;
        
        int nativeSpeed = (int)( rps * motor.getCountPerRotation());
        
        if( Math.abs(nativeSpeed) > Math.abs( motor.getMaxSpeed()) ) {
            throw new IllegalArgumentException(String.format("Speed [%f rpm / %d] exceeds motor capacity: %d", rpm, nativeSpeed, motor.getMaxSpeed() ) );
        }
        
        return nativeSpeed;
    }

    public double getRpm() {
        return rpm;
    }
    
    @Override
    public String toString() {
        return String.format("%frpm", rpm);
    }
}
