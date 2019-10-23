package com.gantzgulch.lego.platform.impl;

import java.util.HashMap;
import java.util.Map;

import com.gantzgulch.lego.device.Board;
import com.gantzgulch.lego.device.InputDevice;
import com.gantzgulch.lego.device.OutputDevice;
import com.gantzgulch.lego.logging.EV3Logger;
import com.gantzgulch.lego.platform.Platform;
import com.gantzgulch.lego.platform.PlatformType;
import com.gantzgulch.lego.port.InputPort;
import com.gantzgulch.lego.port.OutputPort;
import com.gantzgulch.lego.util.Closeables;
import com.gantzgulch.lego.util.Pair;

public abstract class AbstractPlatform implements Platform {

    protected final EV3Logger LOG = EV3Logger.getLogger(getClass());

    private static class PlatformSingleton {
        private static final AbstractPlatform INSTANCE = new com.gantzgulch.lego.platform.ev3.EV3Platform();
    }

    public static AbstractPlatform getInstance() {
        return PlatformSingleton.INSTANCE;
    }

    private final PlatformType type;

    private final Board board;

    private final Map<Pair<Class<? extends InputDevice<?>>, InputPort>, InputDevice<?>> inputCache = new HashMap<>();

    private final Map<Pair<Class<? extends OutputDevice<?>>, OutputPort>, OutputDevice<?>> outputCache = new HashMap<>();

    protected AbstractPlatform(final PlatformType type, final Board board) {

        LOG.entering("ctor");

        this.board = board;
        this.type = type;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public PlatformType getType() {
        return type;
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
        
        Closeables.close(board);
        
    }

    protected <D extends InputDevice<?>> D getCachedDevice(final Class<D> deviceClass, final InputPort port) {
        synchronized (inputCache) {
            return deviceClass.cast(inputCache.get(new Pair<>(deviceClass, port)));
        }
    }

    protected void addCachedDevice(final Class<? extends InputDevice<?>> deviceClass, final InputPort port, final InputDevice<?> device) {
        synchronized (inputCache) {
            inputCache.put(new Pair<>(deviceClass, port), device);
        }
    }

    protected <D extends OutputDevice<?>> D getCachedDevice(final Class<D> deviceClass, final OutputPort port) {
        synchronized (outputCache) {
            return deviceClass.cast(outputCache.get(new Pair<>(deviceClass, port)));
        }
    }

    protected void addCachedDevice(final Class<? extends OutputDevice<?>> deviceClass, final OutputPort port, final OutputDevice<?> device) {
        synchronized (outputCache) {
            outputCache.put(new Pair<>(deviceClass, port), device);
        }
    }

    @Override
    public String toString() {
        return type.name();
    }

}
