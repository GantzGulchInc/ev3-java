package com.gantzgulch.lego.platform.device;

import java.nio.file.Path;
import java.util.Set;

import com.gantzgulch.lego.device.Device;
import com.gantzgulch.lego.logging.EV3Logger;
import com.gantzgulch.lego.platform.impl.Attribute;
import com.gantzgulch.lego.platform.impl.AttributeType;
import com.gantzgulch.lego.util.BidirectionalEnumMap;
import com.gantzgulch.lego.util.Closeables;

public class AbstractDevice<CMDS extends Enum<?>> implements Device<CMDS> {

    public static final String ATTR_ADDRESS = "address";
    public static final String ATTR_DRIVER_NAME = "driver_name";
    public static final String ATTR_COMMAND = "command";
    public static final String ATTR_COMMANDS = "commands";

    protected EV3Logger LOG = EV3Logger.getLogger(getClass());

    protected final Path sysFsPath;

    protected final BidirectionalEnumMap<CMDS> commandMap;

    protected final Attribute address;

    protected final Attribute driverName;

    protected final Attribute command;

    protected final Attribute commands;

    public AbstractDevice(final Path sysFsPath, final BidirectionalEnumMap<CMDS> commandMap) {

        this.sysFsPath = sysFsPath;
        this.commandMap = commandMap;

        this.address = new Attribute(AttributeType.READ_ONLY, false, this.sysFsPath, ATTR_ADDRESS);
        this.driverName = new Attribute(AttributeType.READ_ONLY, false, this.sysFsPath, ATTR_DRIVER_NAME);
        this.command = new Attribute(AttributeType.WRITE_ONLY, true, this.sysFsPath, ATTR_COMMAND);
        this.commands = new Attribute(AttributeType.READ_ONLY, false, this.sysFsPath, ATTR_COMMANDS);
    }

    @Override
    public void close() {
        Closeables.close(address);
        Closeables.close(driverName);
        Closeables.close(command);
        Closeables.close(commands);
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
    public Set<CMDS> getCommands() {
        return commandMap.get(commands.readStringArray());
    }

    @Override
    public void sendCommand(final CMDS command) {
        this.command.writeEnum(command, commandMap);
    }

}
