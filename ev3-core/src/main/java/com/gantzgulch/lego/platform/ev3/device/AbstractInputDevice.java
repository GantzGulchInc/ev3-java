package com.gantzgulch.lego.platform.ev3.device;

import java.nio.file.Path;

import com.gantzgulch.lego.util.BidirectionalEnumMap;

public class AbstractInputDevice<CMDS extends Enum<?>> extends AbstractDevice<CMDS> {

    public AbstractInputDevice(final Path sysFsPath, final BidirectionalEnumMap<CMDS> commandMap) {
        
        super(sysFsPath, commandMap);
    }

    @Override
    public void close() {
        super.close();
    }
    
}
