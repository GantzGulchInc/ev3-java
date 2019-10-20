package com.gantzgulch.ev3.platform.ev3.device;

import java.nio.file.Path;

import com.gantzgulch.ev3.device.EV3SensorCommand;
import com.gantzgulch.ev3.device.EV3TouchSensor;
import com.gantzgulch.ev3.platform.ev3.EV3SensorCommandMap;

public class EV3TouchSensorImpl extends AbstractDevice<EV3SensorCommand> implements EV3TouchSensor {

    public EV3TouchSensorImpl(final Path sysFsPath) {
        super(sysFsPath, EV3SensorCommandMap.INSTANCE);
    }

    @Override
    public int getValue() {
        return 0;
    }

}
