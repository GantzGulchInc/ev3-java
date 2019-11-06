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
package com.gantzgulch.lego.api.wheel;

import com.gantzgulch.lego.api.unit.Length;

public final class Wheels {

    private Wheels() {
    }

    public static final Wheel EV3EducationRim = new WheelImpl(Length.ofMillimeters(43.0), Length.ofMillimeters(26.0));

    public static final Wheel EV3EducationTire = new WheelImpl(Length.ofMillimeters(56.0), Length.ofMillimeters(28.0));

    public static final Wheel EV3Rim = new WheelImpl(Length.ofMillimeters(30.0), Length.ofMillimeters(20));

    public static final Wheel EV3Tire = new WheelImpl(Length.ofMillimeters(43.2), Length.ofMillimeters(21.0));

}
