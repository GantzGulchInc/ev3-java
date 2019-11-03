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

import com.gantzgulch.lego.device.ev3.EV3Motor.EV3MotorPolarity;
import com.gantzgulch.lego.util.lang.BidirectionalEnumMap;

public class EV3MotorPolarityMap extends BidirectionalEnumMap<EV3MotorPolarity> {

    public static final EV3MotorPolarityMap INSTANCE = new EV3MotorPolarityMap();

    public EV3MotorPolarityMap() {
        
        super( EV3MotorPolarity.values() );
        
        add(EV3MotorPolarity.NORMAL, "normal");
        add(EV3MotorPolarity.INVERSED, "inversed");
    }

}
