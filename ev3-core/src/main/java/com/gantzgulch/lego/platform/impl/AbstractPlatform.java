package com.gantzgulch.lego.platform.impl;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import com.gantzgulch.lego.device.Board;
import com.gantzgulch.lego.device.Device;
import com.gantzgulch.lego.device.InputDevice;
import com.gantzgulch.lego.device.OutputDevice;
import com.gantzgulch.lego.device.Port;
import com.gantzgulch.lego.logging.EV3Logger;
import com.gantzgulch.lego.platform.Platform;
import com.gantzgulch.lego.platform.PlatformType;
import com.gantzgulch.lego.platform.brickpi.BrickPiPlatform;
import com.gantzgulch.lego.platform.device.BoardImpl;
import com.gantzgulch.lego.platform.ev3.EV3Platform;
import com.gantzgulch.lego.port.InputPort;
import com.gantzgulch.lego.port.OutputPort;
import com.gantzgulch.lego.util.Closeables;
import com.gantzgulch.lego.util.Pair;

public abstract class AbstractPlatform implements Platform {

    protected final EV3Logger LOG = EV3Logger.getLogger(getClass());

    private static Platform instance = null;

    public synchronized static Platform getInstance() {

        if (instance == null) {

            try (final Board board = new BoardImpl(Path.of("/sys/class/board-info/board0"))) {

                if (board.getModel().contains("Raspberry")) {
                    instance = new BrickPiPlatform();
                } else {
                    instance = new EV3Platform();
                }
                
            }catch(final IOException e) {
                throw new RuntimeException(e);
            }

        }

        return instance;
    }

    private final PlatformType type;

    private final Board[] boards;

    private final Map<Pair<Class<? extends InputDevice<?>>, InputPort>, InputDevice<?>> inputCache = new HashMap<>();

    private final Map<Pair<Class<? extends OutputDevice<?>>, OutputPort>, OutputDevice<?>> outputCache = new HashMap<>();

    protected AbstractPlatform(//
            final PlatformType type, //
            final Board[] board) {

        LOG.entering("ctor");

        this.boards = board;
        this.type = type;
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

    protected abstract <D extends Device<?>> D findDeviceImpl(final Class<D> deviceClass, final Port port);

    @Override
    public String toString() {
        return type.name();
    }

}
