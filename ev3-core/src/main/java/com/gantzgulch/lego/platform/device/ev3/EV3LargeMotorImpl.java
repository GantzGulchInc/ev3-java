package com.gantzgulch.lego.platform.device.ev3;

import java.nio.file.Path;

import com.gantzgulch.lego.device.ev3.EV3LargeMotor;
import com.gantzgulch.lego.platform.device.AbstractTachoMotor;
import com.gantzgulch.lego.platform.ev3.EV3MotorCommandMap;
import com.gantzgulch.lego.platform.ev3.EV3MotorPolarityMap;
import com.gantzgulch.lego.platform.ev3.EV3MotorStateMap;
import com.gantzgulch.lego.platform.ev3.EV3MotorStopActionMap;

public class EV3LargeMotorImpl extends AbstractTachoMotor implements EV3LargeMotor {

    public EV3LargeMotorImpl(final Path sysFsPath) {
        super(sysFsPath, EV3MotorCommandMap.INSTANCE, EV3MotorStopActionMap.INSTANCE, EV3MotorStateMap.INSTANCE, EV3MotorPolarityMap.INSTANCE);
    }

    @Override
    public void close() {
        super.close();
    }
}
