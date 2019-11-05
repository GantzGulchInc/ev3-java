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

import com.gantzgulch.lego.api.device.ev3.EV3MotorCommand;
import com.gantzgulch.lego.common.lang.BidirectionalEnumMap;

public class EV3MotorCommandMap extends BidirectionalEnumMap<EV3MotorCommand> {

    public static final EV3MotorCommandMap INSTANCE = new EV3MotorCommandMap();
    
    public EV3MotorCommandMap() {
        
        super(EV3MotorCommand.values());
        
        add(EV3MotorCommand.RESET, "reset");
        add(EV3MotorCommand.RUN_DIRECT, "run-direct");
        add(EV3MotorCommand.RUN_FOREVER, "run-forever");
        add(EV3MotorCommand.RUN_TIMED, "run-timed");
        add(EV3MotorCommand.RUN_TO_ABS_POS, "run-to-abs-pos");
        add(EV3MotorCommand.RUN_TO_REL_POS, "run-to-rel-pos");
        add(EV3MotorCommand.STOP, "stop");
    }

}
