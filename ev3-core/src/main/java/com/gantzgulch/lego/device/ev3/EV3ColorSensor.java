package com.gantzgulch.lego.device.ev3;

import com.gantzgulch.lego.device.ev3.EV3ColorSensor.EV3ColorSensorMode;
import com.gantzgulch.lego.device.ev3.EV3Sensor.EV3SensorCommand;

public interface EV3ColorSensor extends EV3Sensor<EV3SensorCommand, EV3ColorSensorMode> {

    public enum EV3ColorSensorMode {
        COL_REFLECT, //
        COL_AMBIENT, //
        COL_COLOR, //
        REF_RAW, //
        RGB_RAW, //
        COL_CAL;
    }
}
