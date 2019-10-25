package com.gantzgulch.lego.platform.brickpi;

import com.gantzgulch.lego.port.InputPort;
import com.gantzgulch.lego.util.BidirectionalEnumMap;

public class BrickPiInputPortMap extends BidirectionalEnumMap<InputPort> {

    public static final BrickPiInputPortMap INSTANCE = new BrickPiInputPortMap();
    
    public BrickPiInputPortMap() {
        
        super( InputPort.values() );
        
        add(InputPort.PORT_1, "spi0.1:S1");
        add(InputPort.PORT_2, "spi0.1:S2");
        add(InputPort.PORT_3, "spi0.1:S3");
        add(InputPort.PORT_4, "spi0.1:S4");
    }

}
