package com.gantzgulch.lego.platform.ev3.device;

import java.nio.file.Path;

import com.gantzgulch.lego.util.BidirectionalEnumMap;

public class AbstractOutputDevice<CMDS extends Enum<?>> extends AbstractDevice<CMDS> {

    public AbstractOutputDevice(final Path sysFsPath, final BidirectionalEnumMap<CMDS> commandMap) {
        
        super(sysFsPath, commandMap);
    }

    @Override
    public void close() {
        super.close();
    }
    

}
