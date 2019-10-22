package com.gantzgulch.lego.platform.ev3;

import com.gantzgulch.lego.device.ev3.EV3Motor.EV3MotorPolarity;
import com.gantzgulch.lego.util.BidirectionalEnumMap;

public class EV3MotorPolarityMap extends BidirectionalEnumMap<EV3MotorPolarity> {

    public static final EV3MotorPolarityMap INSTANCE = new EV3MotorPolarityMap();

    public EV3MotorPolarityMap() {
        
        super( EV3MotorPolarity.values() );
        
        add(EV3MotorPolarity.NORMAL, "normal");
        add(EV3MotorPolarity.INVERSED, "inversed");
    }

}
