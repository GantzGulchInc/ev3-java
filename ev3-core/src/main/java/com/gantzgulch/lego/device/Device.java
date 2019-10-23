package com.gantzgulch.lego.device;

import java.io.Closeable;
import java.util.Set;

public interface Device<CMDS extends Enum<?>> extends Closeable {

    String getAddress();
    
    String getDriverName();
    
    Set<CMDS> getCommands();
    
    void sendCommand(CMDS command);
}
