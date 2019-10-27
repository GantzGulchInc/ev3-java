package com.gantzgulch.lego.platform.ev3;

import com.gantzgulch.lego.device.InputPort;
import com.gantzgulch.lego.util.lang.BidirectionalEnumMap;

public class EV3InputPortMap extends BidirectionalEnumMap<InputPort> {

    public static final EV3InputPortMap INSTANCE = new EV3InputPortMap();
    
    public EV3InputPortMap() {
        
        super( InputPort.values() );
        
        add(InputPort.PORT_1, "ev3-ports:in1");
        add(InputPort.PORT_2, "ev3-ports:in2");
        add(InputPort.PORT_3, "ev3-ports:in3");
        add(InputPort.PORT_4, "ev3-ports:in4");
    }

}
