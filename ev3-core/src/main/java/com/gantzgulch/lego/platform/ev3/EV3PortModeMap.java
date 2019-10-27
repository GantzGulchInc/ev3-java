package com.gantzgulch.lego.platform.ev3;

import com.gantzgulch.lego.device.Port.PortMode;
import com.gantzgulch.lego.util.lang.BidirectionalEnumMap;

public class EV3PortModeMap extends BidirectionalEnumMap<PortMode> {

    public static final EV3PortModeMap INSTANCE = new EV3PortModeMap();
    
    public EV3PortModeMap() {
        
        super( PortMode.values() );
        
        add(PortMode.AUTO, "auto");
        add(PortMode.NXT_ANALOG, "nxt-analog");
        add(PortMode.NXT_COLOR, "nxt-color");
        add(PortMode.NXT_I2C, "nxt-i2c");
        add(PortMode.OTHER_I2C, "other-i2c");
        add(PortMode.EV3_ANALOG, "ev3-analog");
        add(PortMode.EV3_UART, "ev3-uart");
        add(PortMode.OTHER_UART, "other-uart");
        add(PortMode.RAW, "raw");
    }

}
