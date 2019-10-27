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