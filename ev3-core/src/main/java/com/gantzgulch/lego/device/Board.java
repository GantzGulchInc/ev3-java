package com.gantzgulch.lego.device;

import java.io.Closeable;

public interface Board extends Closeable {

    String getHwRevision();
    
    String getModel();
    
    String getRomRevision();
    
    String getSerialNumber();
    
    String getType();

}
