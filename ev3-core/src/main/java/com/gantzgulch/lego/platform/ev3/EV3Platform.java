package com.gantzgulch.lego.platform.ev3;

import java.nio.file.Path;
import java.util.Optional;

import com.gantzgulch.lego.device.InputDevice;
import com.gantzgulch.lego.device.OutputDevice;
import com.gantzgulch.lego.platform.PlatformType;
import com.gantzgulch.lego.platform.ev3.EV3DeviceDescriptorMap.DeviceDescriptor;
import com.gantzgulch.lego.platform.ev3.EV3DeviceDescriptorMap.InputDeviceDescriptorMap;
import com.gantzgulch.lego.platform.ev3.EV3DeviceDescriptorMap.OutputDeviceDescriptorMap;
import com.gantzgulch.lego.platform.impl.AbstractPlatform;
import com.gantzgulch.lego.port.InputPort;
import com.gantzgulch.lego.port.OutputPort;

public class EV3Platform extends AbstractPlatform {

    private final OutputDeviceDescriptorMap outputDeviceMap = new OutputDeviceDescriptorMap();

    private final InputDeviceDescriptorMap inputDeviceMap = new InputDeviceDescriptorMap();

    private final DeviceFinder deviceFinder = new DeviceFinder();

    public EV3Platform() {
        super(PlatformType.EV3);
    }

    @Override
    public <D extends InputDevice<?>> D findDevice(final Class<D> deviceClass, final InputPort port) {

        D device = getCachedDevice(deviceClass, port);
        
        if( device != null ) {
            return device;
        }
        
        final Optional<DeviceDescriptor<D, ? extends InputDevice<?>>> dd = inputDeviceMap.find(deviceClass);

        if (dd.isEmpty()) {
            throw new RuntimeException("Device implementation not found: " + deviceClass);
        }

        final String address = InputPortMap.INSTANCE.get(port).orElse(null);

        if (address == null) {
            throw new RuntimeException("Address not found: " + port);
        }

        final Optional<Path> devicePath = deviceFinder.findDevicePath(dd.get().getSysFsClass(), dd.get().getDriverName(), address);

        if( devicePath.isEmpty() ) {
            throw new RuntimeException("Device not found.");
        }

        device = dd.get().createDevice(devicePath.get());
        
        addCachedDevice(deviceClass, port, device);
        
        return device;
    }

    @Override
    public <D extends OutputDevice<?>> D findDevice(final Class<D> deviceClass, final OutputPort port) {

        D device = getCachedDevice(deviceClass, port);
        
        if( device != null ) {
            return device;
        }
        
        final Optional<DeviceDescriptor<D, ? extends OutputDevice<?>>> dd = outputDeviceMap.find(deviceClass);

        if (dd.isEmpty()) {
            throw new RuntimeException("Device implementation not found: " + deviceClass);
        }

        final String address = OutputPortMap.INSTANCE.get(port).orElse(null);

        if (address == null) {
            throw new RuntimeException("Address not found: " + port);
        }

        final Optional<Path> devicePath = deviceFinder.findDevicePath(dd.get().getSysFsClass(), dd.get().getDriverName(), address);

        if( devicePath.isEmpty() ) {
            throw new RuntimeException("Device not found.");
        }

        device =  dd.get().createDevice(devicePath.get());
        
        addCachedDevice(deviceClass, port, device);
        
        return device;

    }

}
