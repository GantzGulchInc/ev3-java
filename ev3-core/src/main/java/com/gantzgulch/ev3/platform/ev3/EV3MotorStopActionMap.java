package com.gantzgulch.ev3.platform.ev3;

import com.gantzgulch.ev3.device.EV3MotorStopAction;
import com.gantzgulch.ev3.util.BidirectionalEnumMap;

public class EV3MotorStopActionMap extends BidirectionalEnumMap<EV3MotorStopAction> {

    public static final EV3MotorStopActionMap INSTANCE = new EV3MotorStopActionMap();
    
    public EV3MotorStopActionMap() {
        add(EV3MotorStopAction.BRAKE, "brake");
        add(EV3MotorStopAction.COAST, "coast");
        add(EV3MotorStopAction.HOLD, "hold");
    }

}
