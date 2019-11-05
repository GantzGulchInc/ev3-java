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

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;

import com.gantzgulch.lego.api.port.Port;
import com.gantzgulch.lego.api.port.PortMode;
import com.gantzgulch.lego.api.port.PortType;
import com.gantzgulch.lego.common.lang.BidirectionalEnumMap;
import com.gantzgulch.lego.common.lang.Closeables;
import com.gantzgulch.lego.ev3dev.attribute.Attribute;
import com.gantzgulch.lego.ev3dev.attribute.AttributeType;

public class PortImpl implements Port {

    private final PortType type;
    private final Path sysFsPath;
    private final BidirectionalEnumMap<PortMode> portModeMap;

    private final Attribute address;
    private final Attribute driverName;
    private final Attribute mode;
    private final Attribute modes;

    public PortImpl(final PortType type, final Path sysFsPath, final BidirectionalEnumMap<PortMode> portModeMap) {

        this.type = type;
        this.sysFsPath = sysFsPath;
        this.portModeMap = portModeMap;
        this.address = createAttribute(AttributeType.READ_ONLY_CACHED, false, this.sysFsPath, "address");
        this.driverName = createAttribute(AttributeType.READ_ONLY, false, this.sysFsPath, "driver_name");
        this.mode = createAttribute(AttributeType.READ_WRITE, false, this.sysFsPath, "mode");
        this.modes = createAttribute(AttributeType.READ_ONLY, false, this.sysFsPath, "modes");
    }

    @Override
    public void close() throws IOException {
        Closeables.close(address);
        Closeables.close(driverName);
        Closeables.close(mode);
        Closeables.close(modes);
    }

    @Override
    public PortType getType() {
        return type;
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
    public PortMode getMode() {
        return mode.readEnum(portModeMap).orElse(PortMode.AUTO);
    }

    @Override
    public void setMode(final PortMode newMode) {
        mode.writeEnum(newMode, portModeMap);
    }

    @Override
    public Set<PortMode> getModes() {
        return portModeMap.get(modes.readStringArray());
    }
}
