package com.gantzgulch.lego.platform.ev3;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.gantzgulch.lego.device.Board;
import com.gantzgulch.lego.device.Device;
import com.gantzgulch.lego.device.Port;
import com.gantzgulch.lego.device.Port.PortType;
import com.gantzgulch.lego.device.ev3.EV3Led;
import com.gantzgulch.lego.device.ev3.EV3Led.LedColor;
import com.gantzgulch.lego.platform.PlatformType;
import com.gantzgulch.lego.platform.common.AbstractPlatform;
import com.gantzgulch.lego.platform.common.DeviceDescriptorMap.DeviceDescriptor;
import com.gantzgulch.lego.platform.common.DeviceFinder;
import com.gantzgulch.lego.platform.common.EV3LedTriggerMap;
import com.gantzgulch.lego.platform.common.device.BoardImpl;
import com.gantzgulch.lego.platform.common.device.PortImpl;
import com.gantzgulch.lego.platform.ev3.device.EV3LedImpl;
import com.gantzgulch.lego.util.exception.DeviceNotFoundException;
import com.gantzgulch.lego.util.exception.PortNotFoundException;
import com.gantzgulch.lego.util.lang.Pair;

public class EV3Platform extends AbstractPlatform {

    private final Ev3DeviceDescriptorMap deviceMap = new Ev3DeviceDescriptorMap();

    private final DeviceFinder deviceFinder = new DeviceFinder();

    private final Map<Pair<Integer,LedColor>, EV3Led> ledMap = createLedMap();
    
    public EV3Platform() {
        super(PlatformType.EV3, createBoards(), EV3OutputPortMap.INSTANCE, EV3InputPortMap.INSTANCE);
    }


    @Override
    public void close() {
        super.close();
    }

    @Override
    protected Map<Pair<Integer, LedColor>, EV3Led> getLedMap() {
        return ledMap;
    }

    private Map<Pair<Integer, LedColor>, EV3Led> createLedMap() {
        
        final Map<Pair<Integer, LedColor>, EV3Led> m = new HashMap<>();
        
        m.put( new Pair<>(0,LedColor.GREEN), new EV3LedImpl(Path.of("/sys/class/leds/led0:green:brick-status"), EV3LedTriggerMap.INSTANCE) );
        m.put( new Pair<>(0,LedColor.RED), new EV3LedImpl(Path.of("/sys/class/leds/led0:red:brick-status"), EV3LedTriggerMap.INSTANCE) );
        m.put( new Pair<>(1,LedColor.GREEN), new EV3LedImpl(Path.of("/sys/class/leds/led1:green:brick-status"), EV3LedTriggerMap.INSTANCE) );
        m.put( new Pair<>(1,LedColor.RED), new EV3LedImpl(Path.of("/sys/class/leds/led1:red:brick-status"), EV3LedTriggerMap.INSTANCE) );
        
        return m;
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

        final Board[] boards = new Board[1];

        boards[0] = new BoardImpl(Path.of("/sys/class/board-info/board0/subsystem/board0"));

        return boards;

    }

}
