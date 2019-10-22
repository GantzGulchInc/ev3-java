package com.gantzgulch.lego.platform.ev3;

import com.gantzgulch.lego.device.ev3.EV3Sensor.EV3SensorMode;
import com.gantzgulch.lego.util.BidirectionalEnumMap;

public class EV3SensorModeMap extends BidirectionalEnumMap<EV3SensorMode> {

    public static final EV3SensorModeMap INSTANCE = new EV3SensorModeMap();

    public EV3SensorModeMap() {
    }

}
