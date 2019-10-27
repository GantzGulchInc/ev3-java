package com.gantzgulch.lego.platform.common;

import java.util.HashMap;
import java.util.Map;

import com.gantzgulch.lego.device.Board;
import com.gantzgulch.lego.device.Device;
import com.gantzgulch.lego.device.InputDevice;
import com.gantzgulch.lego.device.InputPort;
import com.gantzgulch.lego.device.OutputDevice;
import com.gantzgulch.lego.device.OutputPort;
import com.gantzgulch.lego.device.Port;
import com.gantzgulch.lego.device.Port.PortType;
import com.gantzgulch.lego.device.ev3.EV3Led;
import com.gantzgulch.lego.device.ev3.EV3Led.LedColor;
import com.gantzgulch.lego.platform.Platform;
import com.gantzgulch.lego.platform.PlatformType;
import com.gantzgulch.lego.util.exception.DeviceNotFoundException;
import com.gantzgulch.lego.util.lang.BidirectionalEnumMap;
import com.gantzgulch.lego.util.lang.Closeables;
import com.gantzgulch.lego.util.lang.Pair;
import com.gantzgulch.lego.util.logger.EV3Logger;

public abstract class AbstractPlatform implements Platform {

    protected final EV3Logger LOG = EV3Logger.getLogger(getClass());

    private final PlatformType type;

    private final Board[] boards;

    private final BidirectionalEnumMap<OutputPort> outputPortAddressMap;
    
    private final BidirectionalEnumMap<InputPort> inputPortAddressMap;

    private final Map<Pair<Class<? extends InputDevice<?>>, InputPort>, InputDevice<?>> inputCache = new HashMap<>();

    private final Map<Pair<Class<? extends OutputDevice<?>>, OutputPort>, OutputDevice<?>> outputCache = new HashMap<>();

    protected AbstractPlatform(//
            final PlatformType type, //
            final Board[] board, //
            final BidirectionalEnumMap<OutputPort> outputPortAddressMap, //
            final BidirectionalEnumMap<InputPort> inputPortAddressMap) {

        this.outputPortAddressMap = outputPortAddressMap;
        this.inputPortAddressMap = inputPortAddressMap;
        LOG.entering("ctor");

        this.boards = board;
        this.type = type;
    }

    @Override
    public int getBoardCount() {
        return boards.length;
    }
    
    @Override
    public Board findBoard(final int boardIndex) {
        return boards[boardIndex];
    }

    @Override
    public PlatformType getType() {
        return type;
    }

    @Override
    public EV3Led findLed(int ledIndex, LedColor ledColor) {
    
        final Map<Pair<Integer,LedColor>, EV3Led> ledMap = getLedMap();
        
        final EV3Led  led = ledMap.get( new Pair<>(ledIndex, ledColor) );
        
        if( led == null ) {
            throw new DeviceNotFoundException("Unable to find led.");
        }

        return led;
    }
    
    
    @Override
    public Port findPort(final InputPort port) {
        return findPort(PortType.INPUT, inputPortAddressMap.get(port).orElse(""));
    }

    @Override
    public Port findPort(final OutputPort port) {
        return findPort(PortType.OUTPUT, outputPortAddressMap.get(port).orElse(""));
    }

    
    @Override
    public void close() {

        synchronized (inputCache) {

            inputCache//
                    .values() //
                    .forEach(Closeables::close);

            inputCache.clear();
        }

        synchronized (outputCache) {

            outputCache //
                    .values() //
                    .forEach(Closeables::close);

            outputCache.clear();
        }

        Closeables.close(boards);

    }

    public <D extends InputDevice<?>> D findDevice(final Class<D> deviceClass, final InputPort port) {

        synchronized (inputCache) {

            final Pair<Class<? extends InputDevice<?>>, InputPort> key = new Pair<>(deviceClass, port);

            D device = deviceClass.cast(inputCache.get(key));

            if (device != null) {
                return device;
            }

            final Port realPort = findPort(port);

            device = findDeviceImpl(deviceClass, realPort);

            inputCache.put(key, device);

            return device;
        }
    }

    public <D extends OutputDevice<?>> D findDevice(final Class<D> deviceClass, final OutputPort port) {

        synchronized (outputCache) {

            final Pair<Class<? extends OutputDevice<?>>, OutputPort> key = new Pair<>(deviceClass, port);

            D device = deviceClass.cast(outputCache.get(key));

            if (device != null) {
                return device;
            }

            final Port realPort = findPort(port);

            device = findDeviceImpl(deviceClass, realPort);

            outputCache.put(key, device);

            return device;
        }

    }

    @Override
    public String toString() {
        return type.name();
    }

    protected abstract Port findPort(final PortType type, final String address);
    
    protected abstract <D extends Device<?>> D findDeviceImpl(final Class<D> deviceClass, final Port port);

    protected abstract Map<Pair<Integer,LedColor>, EV3Led> getLedMap();
}
