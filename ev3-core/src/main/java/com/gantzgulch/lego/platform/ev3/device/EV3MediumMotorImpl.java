package com.gantzgulch.lego.platform.ev3.device;

import java.nio.file.Path;

import com.gantzgulch.lego.device.ev3.EV3MediumMotor;
import com.gantzgulch.lego.platform.ev3.EV3MotorCommandMap;
import com.gantzgulch.lego.platform.ev3.EV3MotorPolarityMap;
import com.gantzgulch.lego.platform.ev3.EV3MotorStateMap;
import com.gantzgulch.lego.platform.ev3.EV3MotorStopActionMap;

public class EV3MediumMotorImpl extends AbstractTachoMotor implements EV3MediumMotor {

    public EV3MediumMotorImpl(final Path sysFsPath) {
        super(sysFsPath, EV3MotorCommandMap.INSTANCE, EV3MotorStopActionMap.INSTANCE, EV3MotorStateMap.INSTANCE, EV3MotorPolarityMap.INSTANCE);
    }

}
