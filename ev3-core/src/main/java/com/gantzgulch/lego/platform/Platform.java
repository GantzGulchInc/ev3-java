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
package com.gantzgulch.lego.platform;

import java.io.Closeable;

import com.gantzgulch.lego.device.Board;
import com.gantzgulch.lego.device.InputDevice;
import com.gantzgulch.lego.device.InputPort;
import com.gantzgulch.lego.device.OutputDevice;
import com.gantzgulch.lego.device.OutputPort;
import com.gantzgulch.lego.device.Port;
import com.gantzgulch.lego.device.ev3.EV3Led;
import com.gantzgulch.lego.device.ev3.EV3Led.LedColor;
import com.gantzgulch.lego.platform.common.PlatformFactory;
import com.gantzgulch.lego.util.exception.DeviceNotFoundException;
import com.gantzgulch.lego.util.exception.PortNotFoundException;

public interface Platform extends Closeable {

    PlatformType getType();

    int getBoardCount();
    
    Board findBoard(int boardIndex);

    EV3Led findLed(int ledIndex, LedColor ledColor);

    Port findPort(OutputPort port) throws PortNotFoundException;

    Port findPort(InputPort port) throws PortNotFoundException;

    <D extends OutputDevice<?>> D findDevice(Class<D> deviceClass, OutputPort port) throws DeviceNotFoundException;

    <D extends InputDevice<?>> D findDevice(Class<D> deviceClass, InputPort port) throws DeviceNotFoundException;

    public static Platform getInstance() {
        return PlatformFactory.INSTANCE.getPlatform();
    }
}
