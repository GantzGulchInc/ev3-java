package com.gantzgulch.lego.platform.common.device;

import java.nio.file.Path;

import com.gantzgulch.lego.util.lang.BidirectionalEnumMap;

public class AbstractOutputDevice<CMDS extends Enum<?>> extends AbstractDevice<CMDS> {

    public AbstractOutputDevice(final Path sysFsPath, final BidirectionalEnumMap<CMDS> commandMap) {
        
        super(sysFsPath, commandMap);
    }

    @Override
    public void close() {
        super.close();
    }
    

}
