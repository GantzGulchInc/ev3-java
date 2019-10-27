package com.gantzgulch.lego.platform.ev3;

import com.gantzgulch.lego.device.OutputPort;
import com.gantzgulch.lego.util.lang.BidirectionalEnumMap;

public class EV3OutputPortMap extends BidirectionalEnumMap<OutputPort> {

    public static final EV3OutputPortMap INSTANCE = new EV3OutputPortMap();
    
    public EV3OutputPortMap() {
        
        super(OutputPort.values());
        
        add(OutputPort.PORT_A, "ev3-ports:outA");
        add(OutputPort.PORT_B, "ev3-ports:outB");
        add(OutputPort.PORT_C, "ev3-ports:outC");
        add(OutputPort.PORT_D, "ev3-ports:outD");
    }

}
