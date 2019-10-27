package com.gantzgulch.lego.platform.brickpi;

import com.gantzgulch.lego.device.OutputPort;
import com.gantzgulch.lego.util.lang.BidirectionalEnumMap;

public class BrickPiOutputPortMap extends BidirectionalEnumMap<OutputPort> {

    public static final BrickPiOutputPortMap INSTANCE = new BrickPiOutputPortMap();
    
    public BrickPiOutputPortMap() {
        
        super(OutputPort.values());
        
        add(OutputPort.PORT_A, "spi0.1:MA");
        add(OutputPort.PORT_B, "spi0.1:MB");
        add(OutputPort.PORT_C, "spi0.1:MC");
        add(OutputPort.PORT_D, "spi0.1:MD");
    }

}
