/*******************************************************************************
 *    Copyright 2019 GantzGulch, Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *******************************************************************************/
package com.gantzgulch.lego.ev3dev.device;

import static com.gantzgulch.lego.ev3dev.attribute.AttributeFactory.createAttribute;

import java.nio.file.Path;
import java.util.Set;

import com.gantzgulch.lego.api.device.Device;
import com.gantzgulch.lego.common.lang.BidirectionalEnumMap;
import com.gantzgulch.lego.common.lang.Closeables;
import com.gantzgulch.lego.common.logger.EV3Logger;
import com.gantzgulch.lego.ev3dev.attribute.Attribute;
import com.gantzgulch.lego.ev3dev.attribute.AttributeType;

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

        this.address = createAttribute(AttributeType.READ_ONLY_CACHED, false, this.sysFsPath, ATTR_ADDRESS);
        this.driverName = createAttribute(AttributeType.READ_ONLY_CACHED, false, this.sysFsPath, ATTR_DRIVER_NAME);
        this.command = createAttribute(AttributeType.WRITE_ONLY, true, this.sysFsPath, ATTR_COMMAND);
        this.commands = createAttribute(AttributeType.READ_ONLY, false, this.sysFsPath, ATTR_COMMANDS);
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
