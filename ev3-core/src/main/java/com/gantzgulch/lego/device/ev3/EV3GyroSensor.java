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
