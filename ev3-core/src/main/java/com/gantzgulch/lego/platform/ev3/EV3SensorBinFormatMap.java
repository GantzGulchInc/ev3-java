package com.gantzgulch.lego.platform.ev3;

import com.gantzgulch.lego.device.ev3.EV3Sensor.EV3SensorBinFormat;
import com.gantzgulch.lego.util.BidirectionalEnumMap;

public class EV3SensorBinFormatMap extends BidirectionalEnumMap<EV3SensorBinFormat> {

    public static final EV3SensorBinFormatMap INSTANCE = new EV3SensorBinFormatMap();

    public EV3SensorBinFormatMap() {
        add(EV3SensorBinFormat.U8, "u8");
        add(EV3SensorBinFormat.S8, "s8");
        add(EV3SensorBinFormat.U16, "u16");
        add(EV3SensorBinFormat.S16, "s16");
        add(EV3SensorBinFormat.S16_BE, "s16_be");
        add(EV3SensorBinFormat.S32, "s32");
        add(EV3SensorBinFormat.S32_BE, "s32_be");
        add(EV3SensorBinFormat.FLOAT, "float");
    }

}
