package com.gantzgulch.lego.platform.brickpi;

import java.nio.file.Path;

import com.gantzgulch.lego.device.InputDevice;
import com.gantzgulch.lego.device.OutputDevice;
import com.gantzgulch.lego.platform.PlatformType;
import com.gantzgulch.lego.platform.ev3.device.EV3Board;
import com.gantzgulch.lego.platform.impl.AbstractPlatform;
import com.gantzgulch.lego.port.InputPort;
import com.gantzgulch.lego.port.OutputPort;

public class BrickPiPlatform extends AbstractPlatform {

    public BrickPiPlatform() {
        super(PlatformType.BRICK_PI, new EV3Board(Path.of("/sys/class/board-info/board0/subsystem/board0")));
    }

    @Override
    public <D extends OutputDevice<?>> D findDevice(Class<D> deviceClass, OutputPort port) {
        return null;
    }

    @Override
    public <D extends InputDevice<?>> D findDevice(Class<D> deviceClass, InputPort port) {
        return null;
    }

}
