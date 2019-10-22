package com.gantzgulch.lego.device;

import java.util.Set;

public interface Device<C extends Enum<?>> {

    String getAddress();
    
    String getDriverName();
    
    Set<C> getCommands();
    
    void setCommand(C command);
}
