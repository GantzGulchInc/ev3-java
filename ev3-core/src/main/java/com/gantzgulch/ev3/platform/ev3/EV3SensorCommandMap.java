package com.gantzgulch.ev3.platform.ev3;

import com.gantzgulch.ev3.device.EV3SensorCommand;
import com.gantzgulch.ev3.util.BidirectionalEnumMap;

public class EV3SensorCommandMap extends BidirectionalEnumMap<EV3SensorCommand> {

    public static final EV3SensorCommandMap INSTANCE = new EV3SensorCommandMap();

    public EV3SensorCommandMap() {

    }

}
