package com.gantzgulch.lego.platform;

import java.io.Closeable;

import com.gantzgulch.lego.device.Board;
import com.gantzgulch.lego.device.InputDevice;
import com.gantzgulch.lego.device.OutputDevice;
import com.gantzgulch.lego.device.Port;
import com.gantzgulch.lego.device.led.Led;
import com.gantzgulch.lego.exception.DeviceNotFoundException;
import com.gantzgulch.lego.exception.PortNotFoundException;
import com.gantzgulch.lego.platform.impl.PlatformFactory;
import com.gantzgulch.lego.port.InputPort;
import com.gantzgulch.lego.port.OutputPort;

public interface Platform extends Closeable {

    PlatformType getType();

    Board findBoard(int boardIndex);

    Led findLed(int ledIndex, int ledColor);

    Port findPort(OutputPort port) throws PortNotFoundException;

    Port findPort(InputPort port) throws PortNotFoundException;

    <D extends OutputDevice<?>> D findDevice(Class<D> deviceClass, OutputPort port) throws DeviceNotFoundException;

    <D extends InputDevice<?>> D findDevice(Class<D> deviceClass, InputPort port) throws DeviceNotFoundException;

    public static Platform getInstance() {
        return PlatformFactory.INSTANCE.getPlatform();
    }
}
