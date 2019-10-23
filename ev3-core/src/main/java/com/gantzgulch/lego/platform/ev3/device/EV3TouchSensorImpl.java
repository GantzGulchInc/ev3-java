package com.gantzgulch.lego.platform.ev3.device;

import java.nio.file.Path;

import com.gantzgulch.lego.device.ev3.EV3Sensor.EV3SensorCommand;
import com.gantzgulch.lego.device.ev3.EV3TouchSensor;
import com.gantzgulch.lego.device.ev3.EV3TouchSensor.EV3TouchSensorMode;
import com.gantzgulch.lego.platform.ev3.EV3SensorBinFormatMap;
import com.gantzgulch.lego.platform.ev3.EV3SensorCommandMap;
import com.gantzgulch.lego.util.BidirectionalEnumMap;

public class EV3TouchSensorImpl extends AbstractSensorDevice<EV3SensorCommand, EV3TouchSensorMode> implements EV3TouchSensor {

    public EV3TouchSensorImpl(final Path sysFsPath) {
        super(sysFsPath, EV3SensorCommandMap.INSTANCE, EV3SensorBinFormatMap.INSTANCE, EV3TouchSensorModeMap.INSTANCE);
    }

    @Override
    public void close() {
        super.close();
    }

    public static class EV3TouchSensorModeMap extends BidirectionalEnumMap<EV3TouchSensorMode> {

        public static final EV3TouchSensorModeMap INSTANCE = new EV3TouchSensorModeMap();

        public EV3TouchSensorModeMap() {

            super(EV3TouchSensorMode.values());

            add(EV3TouchSensorMode.TOUCH, "TOUCH");
        }

    }

}
