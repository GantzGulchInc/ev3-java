package com.gantzgulch.ev3.platform.ev3;

import com.gantzgulch.ev3.device.EV3MotorState;
import com.gantzgulch.ev3.util.BidirectionalEnumMap;

public class EV3MotorStateMap extends BidirectionalEnumMap<EV3MotorState> {

    public static final EV3MotorStateMap INSTANCE = new EV3MotorStateMap();
    
    public EV3MotorStateMap() {
        add(EV3MotorState.HOLDING, "holding");
        add(EV3MotorState.OVERLOADED, "overloaded");
        add(EV3MotorState.RAMPING, "ramping");
        add(EV3MotorState.RUNNING, "running");
        add(EV3MotorState.STALLED, "stalled");
    }

}
