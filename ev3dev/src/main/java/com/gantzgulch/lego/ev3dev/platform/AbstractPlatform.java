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
package com.gantzgulch.lego.ev3dev.platform;

import java.util.HashMap;
import java.util.Map;

import com.gantzgulch.lego.api.device.Board;
import com.gantzgulch.lego.api.device.Device;
import com.gantzgulch.lego.api.device.InputDevice;
import com.gantzgulch.lego.api.device.OutputDevice;
import com.gantzgulch.lego.api.device.ev3.EV3Led;
import com.gantzgulch.lego.api.device.ev3.EV3LedColor;
import com.gantzgulch.lego.api.exception.DeviceNotFoundException;
import com.gantzgulch.lego.api.platform.Platform;
import com.gantzgulch.lego.api.port.InputPort;
import com.gantzgulch.lego.api.port.OutputPort;
import com.gantzgulch.lego.api.port.Port;
import com.gantzgulch.lego.api.port.PortType;
import com.gantzgulch.lego.common.lang.BidirectionalEnumMap;
import com.gantzgulch.lego.common.lang.Closeables;
import com.gantzgulch.lego.common.lang.Pair;
import com.gantzgulch.lego.common.logger.EV3Logger;

public abstract class AbstractPlatform implements Platform {

    protected final EV3Logger LOG = EV3Logger.getLogger(getClass());

    private final String type;

    private final Board[] boards;

    private final BidirectionalEnumMap<OutputPort> outputPortAddressMap;
    
    private final BidirectionalEnumMap<InputPort> inputPortAddressMap;

    private final Map<Pair<Class<? extends InputDevice<?>>, InputPort>, InputDevice<?>> inputCache = new HashMap<>();

    private final Map<Pair<Class<? extends OutputDevice<?>>, OutputPort>, OutputDevice<?>> outputCache = new HashMap<>();

    protected AbstractPlatform(//
            final String type, //
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
    public String getType() {
        return type;
    }

    @Override
    public EV3Led findLed(int ledIndex, EV3LedColor ledColor) {
    
        final Map<Pair<Integer,EV3LedColor>, EV3Led> ledMap = getLedMap();
        
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
        return type;
    }

    protected abstract Port findPort(final PortType type, final String address);
    
    protected abstract <D extends Device<?>> D findDeviceImpl(final Class<D> deviceClass, final Port port);

    protected abstract Map<Pair<Integer,EV3LedColor>, EV3Led> getLedMap();
}
