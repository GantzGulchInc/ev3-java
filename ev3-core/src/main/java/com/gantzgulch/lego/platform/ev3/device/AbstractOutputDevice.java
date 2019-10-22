package com.gantzgulch.lego.platform.ev3.device;

import java.nio.file.Path;

import com.gantzgulch.lego.util.BidirectionalEnumMap;

public class AbstractOutputDevice<C extends Enum<?>> extends AbstractDevice<C> {

    public AbstractOutputDevice(final Path sysFsPath, final BidirectionalEnumMap<C> commandMap) {
        
        super(sysFsPath, commandMap);
    }

}
