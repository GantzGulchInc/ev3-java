package com.gantzgulch.lego.platform.brickpi;

import java.nio.file.Path;
import java.util.Optional;

import com.gantzgulch.lego.device.Board;
import com.gantzgulch.lego.device.Device;
import com.gantzgulch.lego.device.Port;
import com.gantzgulch.lego.device.Port.PortType;
import com.gantzgulch.lego.device.ev3.EV3Led;
import com.gantzgulch.lego.platform.PlatformType;
import com.gantzgulch.lego.platform.common.AbstractPlatform;
import com.gantzgulch.lego.platform.common.DeviceDescriptorMap.DeviceDescriptor;
import com.gantzgulch.lego.platform.common.DeviceFinder;
import com.gantzgulch.lego.platform.common.device.BoardImpl;
import com.gantzgulch.lego.platform.common.device.PortImpl;
import com.gantzgulch.lego.platform.ev3.EV3PortModeMap;
import com.gantzgulch.lego.util.exception.DeviceNotFoundException;
import com.gantzgulch.lego.util.exception.PortNotFoundException;

public class BrickPiPlatform extends AbstractPlatform {

    private final DeviceFinder deviceFinder = new DeviceFinder();

    private final BrickPiDeviceDescriptorMap deviceMap = new BrickPiDeviceDescriptorMap();

    public BrickPiPlatform() {
        super(PlatformType.BRICK_PI, createBoards(), BrickPiOutputPortMap.INSTANCE, BrickPiInputPortMap.INSTANCE);
    }

    @Override
    public EV3Led findLed(int ledIndex, int ledColor) {
        return null;
    }

    protected Port findPort(final PortType type, final String address) {

        final Optional<Path> portSysPath = deviceFinder.findPortPath(address);

        if (portSysPath.isEmpty()) {
            throw new PortNotFoundException("No such port with address: " + address);
        }

        return new PortImpl(type, portSysPath.get(), EV3PortModeMap.INSTANCE);
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
