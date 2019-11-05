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
package com.gantzgulch.lego.api.unit;

import com.gantzgulch.lego.api.device.ev3.EV3MotorCommand;
import com.gantzgulch.lego.api.device.ev3.EV3TachoMotor;

public class SpeedDPS implements Speed {

    private final double dps;
    
    public SpeedDPS(final double degreesPerSecond) {
        this.dps = degreesPerSecond;
    }

    @Override
    public int toNative(final EV3TachoMotor<EV3MotorCommand> motor) {
        
        double rps = dps / 360.0;
        
        int nativeSpeed = (int)( rps * motor.getCountPerRotation() );
        
        if( Math.abs(nativeSpeed) > Math.abs( motor.getMaxSpeed()) ) {
            throw new IllegalArgumentException(String.format("Speed [%f dps / %d] exceeds motor capacity: %d", dps, nativeSpeed, motor.getMaxSpeed() ) );
        }
        
        return nativeSpeed;
    }
    
    @Override
    public String toString() {
        return String.format("%fdps", dps);
    }
}
