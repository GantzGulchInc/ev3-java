package com.gantzgulch.lego.platform.common;

import com.gantzgulch.lego.device.ev3.EV3Motor.EV3MotorStopAction;
import com.gantzgulch.lego.util.lang.BidirectionalEnumMap;

public class EV3MotorStopActionMap extends BidirectionalEnumMap<EV3MotorStopAction> {

    public static final EV3MotorStopActionMap INSTANCE = new EV3MotorStopActionMap();
    
    public EV3MotorStopActionMap() {
        
        super(EV3MotorStopAction.values());
        
        add(EV3MotorStopAction.BRAKE, "brake");
        add(EV3MotorStopAction.COAST, "coast");
        add(EV3MotorStopAction.HOLD, "hold");
    }

}
