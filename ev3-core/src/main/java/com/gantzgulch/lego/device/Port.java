package com.gantzgulch.lego.device;

import java.io.Closeable;
import java.util.Set;

public interface Port extends Closeable {

    String getAddress();
    
    String getDriverName();
 
    PortMode getMode();
    
    void setMode(PortMode modes);
    
    Set<PortMode> getModes();
    
    public static enum PortMode {
        
        AUTO, //
        NXT_ANALOG, //
        NXT_COLOR, //
        NXT_I2C, //
        OTHER_I2C, //
        EV3_ANALOG, //
        EV3_UART, //
        OTHER_UART, //
        RAW;
        
    }
}
