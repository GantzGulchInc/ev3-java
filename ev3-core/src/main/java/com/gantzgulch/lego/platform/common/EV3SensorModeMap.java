package com.gantzgulch.lego.platform.common;

import com.gantzgulch.lego.device.ev3.EV3Sensor.EV3SensorMode;
import com.gantzgulch.lego.util.lang.BidirectionalEnumMap;

public class EV3SensorModeMap extends BidirectionalEnumMap<EV3SensorMode> {

    public static final EV3SensorModeMap INSTANCE = new EV3SensorModeMap();

    public EV3SensorModeMap() {
        super(EV3SensorMode.values());
    }

}
