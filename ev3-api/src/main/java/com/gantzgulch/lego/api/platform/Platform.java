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
package com.gantzgulch.lego.api.platform;

import java.io.Closeable;

import com.gantzgulch.lego.api.device.Board;
import com.gantzgulch.lego.api.device.InputDevice;
import com.gantzgulch.lego.api.device.OutputDevice;
import com.gantzgulch.lego.api.device.ev3.EV3Led;
import com.gantzgulch.lego.api.device.ev3.EV3LedColor;
import com.gantzgulch.lego.api.exception.DeviceNotFoundException;
import com.gantzgulch.lego.api.exception.PortNotFoundException;
import com.gantzgulch.lego.api.port.InputPort;
import com.gantzgulch.lego.api.port.OutputPort;
import com.gantzgulch.lego.api.port.Port;

public interface Platform extends Closeable {

    String getType();

    int getBoardCount();
    
    Board findBoard(int boardIndex);

    EV3Led findLed(int ledIndex, EV3LedColor ledColor);

    Port findPort(OutputPort port) throws PortNotFoundException;

    Port findPort(InputPort port) throws PortNotFoundException;

    <D extends OutputDevice<?>> D findDevice(Class<D> deviceClass, OutputPort port) throws DeviceNotFoundException;

    <D extends InputDevice<?>> D findDevice(Class<D> deviceClass, InputPort port) throws DeviceNotFoundException;

}
