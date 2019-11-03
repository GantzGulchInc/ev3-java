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
package com.gantzgulch.lego.platform.ev3;

import com.gantzgulch.lego.device.InputPort;
import com.gantzgulch.lego.util.lang.BidirectionalEnumMap;

public class EV3InputPortMap extends BidirectionalEnumMap<InputPort> {

    public static final EV3InputPortMap INSTANCE = new EV3InputPortMap();
    
    public EV3InputPortMap() {
        
        super( InputPort.values() );
        
        add(InputPort.PORT_1, "ev3-ports:in1");
        add(InputPort.PORT_2, "ev3-ports:in2");
        add(InputPort.PORT_3, "ev3-ports:in3");
        add(InputPort.PORT_4, "ev3-ports:in4");
    }

}
