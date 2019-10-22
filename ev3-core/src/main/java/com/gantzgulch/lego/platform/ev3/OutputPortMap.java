package com.gantzgulch.lego.platform.ev3;

import com.gantzgulch.lego.port.OutputPort;
import com.gantzgulch.lego.util.BidirectionalEnumMap;

public class OutputPortMap extends BidirectionalEnumMap<OutputPort> {

    public static final OutputPortMap INSTANCE = new OutputPortMap();
    
    public OutputPortMap() {
        add(OutputPort.PORT_A, "ev3-ports:outA");
        add(OutputPort.PORT_B, "ev3-ports:outB");
        add(OutputPort.PORT_C, "ev3-ports:outC");
        add(OutputPort.PORT_D, "ev3-ports:outD");
    }

}
