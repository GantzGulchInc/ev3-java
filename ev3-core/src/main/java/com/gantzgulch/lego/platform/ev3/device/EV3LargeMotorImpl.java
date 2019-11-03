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
package com.gantzgulch.lego.platform.ev3.device;

import java.nio.file.Path;

import com.gantzgulch.lego.device.ev3.EV3LargeMotor;
import com.gantzgulch.lego.platform.common.EV3MotorCommandMap;
import com.gantzgulch.lego.platform.common.EV3MotorPolarityMap;
import com.gantzgulch.lego.platform.common.EV3MotorStateMap;
import com.gantzgulch.lego.platform.common.EV3MotorStopActionMap;
import com.gantzgulch.lego.platform.common.device.AbstractTachoMotor;

public class EV3LargeMotorImpl extends AbstractTachoMotor implements EV3LargeMotor {

    public EV3LargeMotorImpl(final Path sysFsPath) {
        super(sysFsPath, EV3MotorCommandMap.INSTANCE, EV3MotorStopActionMap.INSTANCE, EV3MotorStateMap.INSTANCE, EV3MotorPolarityMap.INSTANCE);
    }

    @Override
    public void close() {
        super.close();
    }
}
