package com.gantzgulch.lego.platform;

import java.io.Closeable;

import com.gantzgulch.lego.device.Board;
import com.gantzgulch.lego.device.InputDevice;
import com.gantzgulch.lego.device.InputPort;
import com.gantzgulch.lego.device.OutputDevice;
import com.gantzgulch.lego.device.OutputPort;
import com.gantzgulch.lego.device.Port;
import com.gantzgulch.lego.device.ev3.EV3Led;
import com.gantzgulch.lego.platform.common.PlatformFactory;
import com.gantzgulch.lego.util.exception.DeviceNotFoundException;
import com.gantzgulch.lego.util.exception.PortNotFoundException;

public interface Platform extends Closeable {

    PlatformType getType();

    int getBoardCount();
    
    Board findBoard(int boardIndex);

    EV3Led findLed(int ledIndex, int ledColor);

    Port findPort(OutputPort port) throws PortNotFoundException;

    Port findPort(InputPort port) throws PortNotFoundException;

    <D extends OutputDevice<?>> D findDevice(Class<D> deviceClass, OutputPort port) throws DeviceNotFoundException;

    <D extends InputDevice<?>> D findDevice(Class<D> deviceClass, InputPort port) throws DeviceNotFoundException;

    public static Platform getInstance() {
        return PlatformFactory.INSTANCE.getPlatform();
    }
}
