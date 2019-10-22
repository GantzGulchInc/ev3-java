package com.gantzgulch.lego.platform.ev3.device;

import java.nio.file.Path;

import com.gantzgulch.lego.device.ev3.EV3Sensor.EV3SensorCommand;
import com.gantzgulch.lego.device.ev3.EV3Sensor.EV3SensorMode;
import com.gantzgulch.lego.device.ev3.EV3TouchSensor;
import com.gantzgulch.lego.platform.ev3.EV3SensorBinFormatMap;
import com.gantzgulch.lego.platform.ev3.EV3SensorCommandMap;
import com.gantzgulch.lego.platform.ev3.EV3SensorModeMap;

public class EV3TouchSensorImpl extends AbstractSensorDevice<EV3SensorCommand,EV3SensorMode> implements EV3TouchSensor {

    public EV3TouchSensorImpl(final Path sysFsPath) {
        super(sysFsPath, EV3SensorCommandMap.INSTANCE, EV3SensorBinFormatMap.INSTANCE, EV3SensorModeMap.INSTANCE);
    }
    
  
}
