package com.gantzgulch.lego.platform.ev3;

import com.gantzgulch.lego.device.ev3.EV3Sensor.EV3SensorCommand;
import com.gantzgulch.lego.util.BidirectionalEnumMap;

public class EV3SensorCommandMap extends BidirectionalEnumMap<EV3SensorCommand> {

    public static final EV3SensorCommandMap INSTANCE = new EV3SensorCommandMap();

    public EV3SensorCommandMap() {

    }

}
