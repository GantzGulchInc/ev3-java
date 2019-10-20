package com.gantzgulch.ev3.platform.ev3.device;

import java.nio.file.Path;

import com.gantzgulch.ev3.device.EV3LargeMotor;
import com.gantzgulch.ev3.platform.ev3.EV3MotorCommandMap;
import com.gantzgulch.ev3.platform.ev3.EV3MotorStateMap;
import com.gantzgulch.ev3.platform.ev3.EV3MotorStopActionMap;

public class EV3LargeMotorImpl extends AbstractTachoMotor implements EV3LargeMotor {

    public EV3LargeMotorImpl(final Path sysFsPath) {
        super(sysFsPath, EV3MotorCommandMap.INSTANCE, EV3MotorStopActionMap.INSTANCE, EV3MotorStateMap.INSTANCE);
    }

}
