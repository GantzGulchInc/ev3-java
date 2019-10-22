package com.gantzgulch.lego.device.ev3;

import com.gantzgulch.lego.device.ev3.EV3Sensor.EV3SensorCommand;
import com.gantzgulch.lego.device.ev3.EV3TouchSensor.EV3TouchSensorMode;

public interface EV3TouchSensor extends EV3Sensor<EV3SensorCommand,EV3TouchSensorMode> {


    public enum EV3TouchSensorMode {
        TOUCH;
    }
}
