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
package com.gantzgulch.lego.platform.brickpi;

import com.gantzgulch.lego.device.Device;
import com.gantzgulch.lego.device.ev3.EV3ColorSensor;
import com.gantzgulch.lego.device.ev3.EV3GyroSensor;
import com.gantzgulch.lego.device.ev3.EV3LargeMotor;
import com.gantzgulch.lego.device.ev3.EV3MediumMotor;
import com.gantzgulch.lego.device.ev3.EV3TouchSensor;
import com.gantzgulch.lego.platform.common.DeviceDescriptorMap;
import com.gantzgulch.lego.platform.ev3.device.EV3ColorSensorImpl;
import com.gantzgulch.lego.platform.ev3.device.EV3GyroSensorImpl;
import com.gantzgulch.lego.platform.ev3.device.EV3LargeMotorImpl;
import com.gantzgulch.lego.platform.ev3.device.EV3MediumMotorImpl;
import com.gantzgulch.lego.platform.ev3.device.EV3TouchSensorImpl;

public class BrickPiDeviceDescriptorMap extends DeviceDescriptorMap<Device<?>, Device<?>> {

    public BrickPiDeviceDescriptorMap() {
        //
        // EV3 Sensors
        //
        add(EV3GyroSensor.class, EV3GyroSensorImpl.class, "lego-sensor", "lego-ev3-gyro");
        add(EV3ColorSensor.class, EV3ColorSensorImpl.class, "lego-sensor", "lego-ev3-color");
        add(EV3TouchSensor.class, EV3TouchSensorImpl.class, "lego-sensor", "lego-ev3-touch");

        //
        // EV3 Motors
        //
        add(EV3LargeMotor.class, EV3LargeMotorImpl.class, "tacho-motor", "lego-nxt-motor");
        add(EV3MediumMotor.class, EV3MediumMotorImpl.class, "tacho-motor", "lego-nxt-motor");
    }
}