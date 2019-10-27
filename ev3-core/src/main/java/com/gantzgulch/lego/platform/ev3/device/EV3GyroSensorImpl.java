package com.gantzgulch.lego.platform.ev3.device;

import java.nio.file.Path;

import com.gantzgulch.lego.device.ev3.EV3GyroSensor;
import com.gantzgulch.lego.device.ev3.EV3GyroSensor.EV3GyroSensorMode;
import com.gantzgulch.lego.device.ev3.EV3Sensor.EV3SensorCommand;
import com.gantzgulch.lego.platform.common.EV3SensorBinFormatMap;
import com.gantzgulch.lego.platform.common.EV3SensorCommandMap;
import com.gantzgulch.lego.platform.common.device.AbstractSensorDevice;
import com.gantzgulch.lego.util.lang.BidirectionalEnumMap;

public class EV3GyroSensorImpl extends AbstractSensorDevice<EV3SensorCommand, EV3GyroSensorMode> implements EV3GyroSensor {

    public EV3GyroSensorImpl(final Path sysFsPath) {
        super(sysFsPath, EV3SensorCommandMap.INSTANCE, EV3SensorBinFormatMap.INSTANCE, EV3GyroSensorModemap.INSTANCE);
    }

    @Override
    public void close() {
        super.close();
    }

    public static class EV3GyroSensorModemap extends BidirectionalEnumMap<EV3GyroSensorMode> {

        public static final EV3GyroSensorModemap INSTANCE = new EV3GyroSensorModemap();

        public EV3GyroSensorModemap() {

            super(EV3GyroSensorMode.values());

            add(EV3GyroSensorMode.GYRO_ANG, "GYRO-ANG");
            add(EV3GyroSensorMode.GYRO_RATE, "GYRO-RATE");
            add(EV3GyroSensorMode.GYRO_FAS, "GYRO-FAS");
            add(EV3GyroSensorMode.GYRO_G_AND_A, "GYRO-G&A");
            add(EV3GyroSensorMode.GYRO_CAL, "GYRO-CAL");
            add(EV3GyroSensorMode.TILT_RATE, "TILT-RATE");
            add(EV3GyroSensorMode.TILT_ANG, "TILT-ANG");
        }

    }

}
