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
package com.gantzgulch.lego.device.ev3;

import com.gantzgulch.lego.device.ev3.EV3GyroSensor.EV3GyroSensorMode;
import com.gantzgulch.lego.device.ev3.EV3Sensor.EV3SensorCommand;

public interface EV3GyroSensor extends EV3Sensor<EV3SensorCommand, EV3GyroSensorMode> {

    public enum EV3GyroSensorMode {
        GYRO_ANG, //
        GYRO_RATE, //
        GYRO_FAS, //
        GYRO_G_AND_A, //
        GYRO_CAL, //
        TILT_RATE, //
        TILT_ANG;
    }
}
