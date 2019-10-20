package com.gantzgulch.ev3.platform.ev3.device;

import java.nio.file.Path;
import java.util.Set;

import com.gantzgulch.ev3.device.Device;
import com.gantzgulch.ev3.device.impl.Attribute;
import com.gantzgulch.ev3.device.impl.AttributeType;
import com.gantzgulch.ev3.util.BidirectionalEnumMap;

public class AbstractDevice<C extends Enum<?>> implements Device<C> {

    protected final Path sysFsPath;
    
    protected final BidirectionalEnumMap<C> commandMap;

    protected final Attribute address;
    
    protected final Attribute driverName;
    
    protected final Attribute command;
    
    protected final Attribute commands;

    
    public AbstractDevice(final Path sysFsPath, final BidirectionalEnumMap<C> commandMap) {
        
        this.sysFsPath = sysFsPath;
        this.commandMap = commandMap;
        
        this.address = new Attribute(AttributeType.READ_ONLY, this.sysFsPath.resolve("address"));
        this.driverName = new Attribute(AttributeType.READ_ONLY, this.sysFsPath.resolve("driver_name"));
        this.command = new Attribute(AttributeType.WRITE_ONLY, this.sysFsPath.resolve("command"));
        this.commands = new Attribute(AttributeType.READ_ONLY, this.sysFsPath.resolve("commands"));
    }

    public Path getSysFsPath() {
        return sysFsPath;
    }
    
    @Override
    public String getAddress() {
        return address.readString();
    }
    
    @Override
    public String getDriverName() {
        return driverName.readString();
    }
    
    @Override
    public Set<C> getCommands() {
        return commandMap.get(commands.readStringArray());
    }
    
    @Override
    public void setCommand(final C command) {
        this.command.writeString( commandMap.get(command).orElse("") );
    }
    
}
