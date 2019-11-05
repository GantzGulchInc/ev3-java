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
package com.gantzgulch.lego.platform.brickpi;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.gantzgulch.lego.api.device.Board;
import com.gantzgulch.lego.api.device.Device;
import com.gantzgulch.lego.api.device.ev3.EV3Led;
import com.gantzgulch.lego.api.device.ev3.EV3LedColor;
import com.gantzgulch.lego.api.exception.DeviceNotFoundException;
import com.gantzgulch.lego.api.exception.PortNotFoundException;
import com.gantzgulch.lego.api.port.Port;
import com.gantzgulch.lego.api.port.PortType;
import com.gantzgulch.lego.common.lang.Pair;
import com.gantzgulch.lego.ev3dev.DeviceFinder;
import com.gantzgulch.lego.ev3dev.DeviceDescriptorMap.DeviceDescriptor;
import com.gantzgulch.lego.ev3dev.device.BoardImpl;
import com.gantzgulch.lego.ev3dev.device.PortImpl;
import com.gantzgulch.lego.ev3dev.device.ev3.EV3LedImpl;
import com.gantzgulch.lego.ev3dev.device.ev3.EV3LedTriggerMap;
import com.gantzgulch.lego.ev3dev.platform.AbstractPlatform;
import com.gantzgulch.lego.platform.brickpi.device.MockLed;

public class BrickPiPlatform extends AbstractPlatform {

    private final DeviceFinder deviceFinder = new DeviceFinder();

    private final BrickPiDeviceDescriptorMap deviceMap = new BrickPiDeviceDescriptorMap();

    private final Map<Pair<Integer, EV3LedColor>, EV3Led> ledMap = createLedMap();

    public BrickPiPlatform() {
        super("BrickPi", createBoards(), BrickPiOutputPortMap.INSTANCE, BrickPiInputPortMap.INSTANCE);
    }

    @Override
    protected Map<Pair<Integer, EV3LedColor>, EV3Led> getLedMap() {
        return ledMap;
    }

    private Map<Pair<Integer, EV3LedColor>, EV3Led> createLedMap() {

        final Map<Pair<Integer, EV3LedColor>, EV3Led> m = new HashMap<>();

        m.put(new Pair<>(0, EV3LedColor.GREEN), new MockLed());
        m.put(new Pair<>(0, EV3LedColor.RED), new EV3LedImpl(Path.of("/sys/class/leds/led1:amber:brick-status"), EV3LedTriggerMap.INSTANCE));
        m.put(new Pair<>(1, EV3LedColor.GREEN), new MockLed());
        m.put(new Pair<>(1, EV3LedColor.RED), new MockLed());

        return m;
    }

    protected Port findPort(final PortType type, final String address) {

        final Optional<Path> portSysPath = deviceFinder.findPortPath(address);

        if (portSysPath.isEmpty()) {
            throw new PortNotFoundException("No such port with address: " + address);
        }

        return new PortImpl(type, portSysPath.get(), BrickPiPortModeMap.INSTANCE);
    }

    protected <D extends Device<?>> D findDeviceImpl(final Class<D> deviceClass, final Port port) {

        final Optional<DeviceDescriptor<D, ? extends Device<?>>> dd = deviceMap.find(deviceClass);

        if (dd.isEmpty()) {
            throw new DeviceNotFoundException("No implementation found for: " + deviceClass.getName());
        }

        final String address = port.getAddress();

        final Optional<Path> devicePath = deviceFinder.findDevicePath(dd.get().getSysFsClass(), dd.get().getDriverName(), address);

        if (devicePath.isEmpty()) {
            throw new RuntimeException("Device not found.");
        }

        D device = dd.get().createDevice(devicePath.get());

        return device;
    }

    private static final Board[] createBoards() {

        final Board[] boards = new Board[2];

        boards[0] = new BoardImpl(Path.of("/sys/class/board-info/board0/subsystem/board0"));
        boards[1] = new BoardImpl(Path.of("/sys/class/board-info/board1/subsystem/board1"));

        return boards;

    }

}
