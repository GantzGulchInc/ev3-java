/*******************************************************************************
 *    Copyright 2019 GantzGulch, Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *******************************************************************************/
package com.gantzgulch.lego.platform.common;

import com.gantzgulch.lego.device.ev3.EV3Sensor.EV3SensorBinFormat;
import com.gantzgulch.lego.util.lang.BidirectionalEnumMap;

public class EV3SensorBinFormatMap extends BidirectionalEnumMap<EV3SensorBinFormat> {

    public static final EV3SensorBinFormatMap INSTANCE = new EV3SensorBinFormatMap();

    public EV3SensorBinFormatMap() {
        
        super(EV3SensorBinFormat.values());
        
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
