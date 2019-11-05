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
package com.gantzgulch.lego.platform.brickpi;

import com.gantzgulch.lego.api.port.PortMode;
import com.gantzgulch.lego.common.lang.BidirectionalEnumMap;

public class BrickPiPortModeMap extends BidirectionalEnumMap<PortMode> {

    public static final BrickPiPortModeMap INSTANCE = new BrickPiPortModeMap();
    
    public BrickPiPortModeMap() {
        
        super( PortMode.values() );
        
        add(PortMode.AUTO, "auto");
        add(PortMode.NXT_ANALOG, "nxt-analog");
        add(PortMode.NXT_COLOR, "nxt-color");
        add(PortMode.NXT_I2C, "nxt-i2c");
        add(PortMode.OTHER_I2C, "other-i2c");
        add(PortMode.EV3_ANALOG, "ev3-analog");
        add(PortMode.EV3_UART, "ev3-uart");
        add(PortMode.OTHER_UART, "other-uart");
        add(PortMode.RAW, "raw");
    }

}
