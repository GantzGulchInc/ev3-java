package com.gantzgulch.lego.platform.common;

import com.gantzgulch.lego.device.ev3.EV3Motor.EV3MotorState;
import com.gantzgulch.lego.util.lang.BidirectionalEnumMap;

public class EV3MotorStateMap extends BidirectionalEnumMap<EV3MotorState> {

    public static final EV3MotorStateMap INSTANCE = new EV3MotorStateMap();
    
    public EV3MotorStateMap() {
        
        super(EV3MotorState.values());
        
        add(EV3MotorState.HOLDING, "holding");
        add(EV3MotorState.OVERLOADED, "overloaded");
        add(EV3MotorState.RAMPING, "ramping");
        add(EV3MotorState.RUNNING, "running");
        add(EV3MotorState.STALLED, "stalled");
    }

}
