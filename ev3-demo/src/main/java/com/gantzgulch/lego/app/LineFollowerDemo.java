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
package com.gantzgulch.lego.app;

import com.gantzgulch.lego.api.device.ev3.EV3LargeMotor;
import com.gantzgulch.lego.api.platform.Platform;
import com.gantzgulch.lego.api.port.OutputPort;
import com.gantzgulch.lego.api.wheel.Wheels;
import com.gantzgulch.lego.shell.EV3Shell;
import com.gantzgulch.lego.tank.Tank;

public class LineFollowerDemo implements Runnable {

    private final Platform platform;

    public LineFollowerDemo(final Platform platform) {
        this.platform = platform;
    }

    public void run() {

        final EV3LargeMotor leftMotor = platform.findDevice(EV3LargeMotor.class, OutputPort.PORT_B);

        final EV3LargeMotor rightMotor = platform.findDevice(EV3LargeMotor.class, OutputPort.PORT_C);

        final Tank tank = Tank.create(leftMotor, rightMotor, Wheels.EV3EducationTire);

        final EV3Shell shell = new EV3Shell(tank);

        shell.run();
    }

}
