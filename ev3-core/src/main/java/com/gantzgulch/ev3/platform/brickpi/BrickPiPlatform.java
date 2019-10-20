package com.gantzgulch.ev3.platform.brickpi;

import com.gantzgulch.ev3.device.InputDevice;
import com.gantzgulch.ev3.device.OutputDevice;
import com.gantzgulch.ev3.platform.impl.AbstractPlatform;
import com.gantzgulch.ev3.port.InputPort;
import com.gantzgulch.ev3.port.OutputPort;

public class BrickPiPlatform extends AbstractPlatform {

    public BrickPiPlatform() {
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
