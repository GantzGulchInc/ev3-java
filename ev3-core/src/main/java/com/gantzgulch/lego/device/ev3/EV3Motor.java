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
package com.gantzgulch.lego.device.ev3;

import com.gantzgulch.lego.device.OutputDevice;

public interface EV3Motor<CMDS extends Enum<?>> extends OutputDevice<CMDS> {

    public static enum EV3MotorCommand {
        RUN_FOREVER, //
        RUN_TO_ABS_POS, //
        RUN_TO_REL_POS, //
        RUN_TIMED, //
        RUN_DIRECT, //
        STOP, //
        RESET;
    }

    public static enum EV3MotorPolarity {
        NORMAL, //
        INVERSED;
    }

    public static enum EV3MotorState {
        RUNNING, //
        RAMPING, //
        HOLDING, //
        OVERLOADED, //
        STALLED;
    }

    public static enum EV3MotorStopAction {
        COAST, //
        BRAKE, //
        HOLD; //
    }

}
