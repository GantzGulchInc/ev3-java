package com.gantzgulch.lego.platform.ev3.device;

import java.nio.file.Path;

import com.gantzgulch.lego.device.ev3.EV3ColorSensor;
import com.gantzgulch.lego.device.ev3.EV3ColorSensor.EV3ColorSensorMode;
import com.gantzgulch.lego.device.ev3.EV3Sensor.EV3SensorCommand;
import com.gantzgulch.lego.platform.ev3.EV3SensorBinFormatMap;
import com.gantzgulch.lego.platform.ev3.EV3SensorCommandMap;
import com.gantzgulch.lego.util.BidirectionalEnumMap;

public class EV3ColorSensorImpl extends AbstractSensorDevice<EV3SensorCommand,EV3ColorSensorMode> implements EV3ColorSensor {

    public EV3ColorSensorImpl(final Path sysFsPath) {
        super(sysFsPath, EV3SensorCommandMap.INSTANCE, EV3SensorBinFormatMap.INSTANCE, EV3ColorSensorModemap.INSTANCE);
    }
    
    public static class EV3ColorSensorModemap extends BidirectionalEnumMap<EV3ColorSensorMode> {

        public static final EV3ColorSensorModemap INSTANCE = new EV3ColorSensorModemap();
        
        public EV3ColorSensorModemap() {
            add(EV3ColorSensorMode.COL_REFLECT, "COL-REFLECT");
            add(EV3ColorSensorMode.COL_AMBIENT, "COL-AMBIENT");
            add(EV3ColorSensorMode.COL_COLOR, "COL-COLOR");
            add(EV3ColorSensorMode.REF_RAW, "REF-RAW");
            add(EV3ColorSensorMode.RGB_RAW, "RGB-RAW");
            add(EV3ColorSensorMode.COL_CAL, "COL-CAL");
        }

    }
}
