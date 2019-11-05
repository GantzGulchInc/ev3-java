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
package com.gantzgulch.lego.ev3dev.ev3;

import com.gantzgulch.lego.api.device.ev3.EV3LedColor;
import com.gantzgulch.lego.common.lang.BidirectionalEnumMap;

public class EV3LedColorMap extends BidirectionalEnumMap<EV3LedColor> {

    public static final EV3LedColorMap INSTANCE = new EV3LedColorMap();

    public EV3LedColorMap() {

        super(EV3LedColor.values());

        add(EV3LedColor.GREEN, "green");
        add(EV3LedColor.RED, "red");
    }

}
