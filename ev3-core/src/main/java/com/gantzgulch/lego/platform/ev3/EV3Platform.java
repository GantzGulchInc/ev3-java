package com.gantzgulch.lego.platform.ev3;

import java.nio.file.Path;
import java.util.Optional;

import com.gantzgulch.lego.device.Board;
import com.gantzgulch.lego.device.Device;
import com.gantzgulch.lego.device.Port;
import com.gantzgulch.lego.device.led.Led;
import com.gantzgulch.lego.exception.DeviceNotFoundException;
import com.gantzgulch.lego.exception.PortNotFoundException;
import com.gantzgulch.lego.platform.PlatformType;
import com.gantzgulch.lego.platform.device.BoardImpl;
import com.gantzgulch.lego.platform.impl.AbstractPlatform;
import com.gantzgulch.lego.platform.impl.PortImpl;
import com.gantzgulch.lego.platform.impl.DeviceDescriptorMap.DeviceDescriptor;
import com.gantzgulch.lego.port.InputPort;
import com.gantzgulch.lego.port.OutputPort;

public class EV3Platform extends AbstractPlatform {

    private final Ev3DeviceDescriptorMap deviceMap = new Ev3DeviceDescriptorMap();

    private final DeviceFinder deviceFinder = new DeviceFinder();

    public EV3Platform() {
        super(PlatformType.EV3, createBoards());
    }

    @Override
    public void close() {
        super.close();
    }

    @Override
    public Led findLed(int ledIndex, int ledColor) {
        return null;
    }

    @Override
    public Port findPort(final InputPort port) {
        return findPort(InputPortMap.INSTANCE.get(port).orElse(""));
    }

    @Override
    public Port findPort(final OutputPort port) {
        return findPort(OutputPortMap.INSTANCE.get(port).orElse(""));
    }

    private Port findPort(final String address) {

        final Optional<Path> portSysPath = deviceFinder.findPortPath(address);

        if (portSysPath.isEmpty()) {
            throw new PortNotFoundException("No such port with address: " + address);
        }

        return new PortImpl(portSysPath.get(), PortModeMap.INSTANCE);
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

        final Board[] boards = new Board[1];

        boards[0] = new BoardImpl(Path.of("/sys/class/board-info/board0/subsystem/board0"));

        return boards;

    }

}
