package com.gantzgulch.lego.device;

public interface Board {

    String getHwRevision();
    
    String getModel();
    
    String getRomRevision();
    
    String getSerialNumber();
    
    String getType();

}
