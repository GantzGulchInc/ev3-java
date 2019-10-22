package com.gantzgulch.lego.device;

import java.util.Set;

public interface Device<CMDS extends Enum<?>> {

    String getAddress();
    
    String getDriverName();
    
    Set<CMDS> getCommands();
    
    void sendCommand(CMDS command);
}
