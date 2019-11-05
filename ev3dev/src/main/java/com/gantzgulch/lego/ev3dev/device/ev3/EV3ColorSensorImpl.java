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
package com.gantzgulch.lego.ev3dev.device.ev3;

import java.nio.file.Path;

import com.gantzgulch.lego.api.device.ev3.EV3ColorSensor;
import com.gantzgulch.lego.api.device.ev3.EV3ColorSensorMode;
import com.gantzgulch.lego.api.device.ev3.EV3SensorCommand;
import com.gantzgulch.lego.common.lang.BidirectionalEnumMap;
import com.gantzgulch.lego.ev3dev.device.AbstractSensorDevice;

public class EV3ColorSensorImpl extends AbstractSensorDevice<EV3SensorCommand,EV3ColorSensorMode> implements EV3ColorSensor {

    public EV3ColorSensorImpl(final Path sysFsPath) {
        super(sysFsPath, EV3SensorCommandMap.INSTANCE, EV3SensorBinFormatMap.INSTANCE, EV3ColorSensorModemap.INSTANCE);
    }
    
    @Override
    public void close() {
        super.close();
    }

    public static class EV3ColorSensorModemap extends BidirectionalEnumMap<EV3ColorSensorMode> {

        public static final EV3ColorSensorModemap INSTANCE = new EV3ColorSensorModemap();
        
        public EV3ColorSensorModemap() {
            
            super(EV3ColorSensorMode.values());
            
            add(EV3ColorSensorMode.COL_REFLECT, "COL-REFLECT");
            add(EV3ColorSensorMode.COL_AMBIENT, "COL-AMBIENT");
            add(EV3ColorSensorMode.COL_COLOR, "COL-COLOR");
            add(EV3ColorSensorMode.REF_RAW, "REF-RAW");
            add(EV3ColorSensorMode.RGB_RAW, "RGB-RAW");
            add(EV3ColorSensorMode.COL_CAL, "COL-CAL");
        }

    }
    
    
}
