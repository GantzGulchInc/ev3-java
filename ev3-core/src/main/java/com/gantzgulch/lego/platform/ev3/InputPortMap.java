package com.gantzgulch.lego.platform.ev3;

import com.gantzgulch.lego.port.InputPort;
import com.gantzgulch.lego.util.BidirectionalEnumMap;

public class InputPortMap extends BidirectionalEnumMap<InputPort> {

    public static final InputPortMap INSTANCE = new InputPortMap();
    
    public InputPortMap() {
        
        super( InputPort.values() );
        
        add(InputPort.PORT_1, "ev3-ports:in1");
        add(InputPort.PORT_2, "ev3-ports:in2");
        add(InputPort.PORT_3, "ev3-ports:in3");
        add(InputPort.PORT_4, "ev3-ports:in4");
    }

}
