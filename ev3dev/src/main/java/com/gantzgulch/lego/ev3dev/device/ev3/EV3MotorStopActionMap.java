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

import com.gantzgulch.lego.api.device.ev3.EV3MotorStopAction;
import com.gantzgulch.lego.common.lang.BidirectionalEnumMap;

public class EV3MotorStopActionMap extends BidirectionalEnumMap<EV3MotorStopAction> {

    public static final EV3MotorStopActionMap INSTANCE = new EV3MotorStopActionMap();
    
    public EV3MotorStopActionMap() {
        
        super(EV3MotorStopAction.values());
        
        add(EV3MotorStopAction.BRAKE, "brake");
        add(EV3MotorStopAction.COAST, "coast");
        add(EV3MotorStopAction.HOLD, "hold");
    }

}
