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
package com.gantzgulch.lego.wheel;

import com.gantzgulch.lego.units.LengthUnit;
import com.gantzgulch.lego.wheel.impl.WheelImpl;

public final class Wheels {

    private Wheels() {
    }

    public static final Wheel EV3EducationRim = new WheelImpl(43.0, LengthUnit.MILLIMETER, 26.0, LengthUnit.MILLIMETER);

    public static final Wheel EV3EducationTire = new WheelImpl(56.0, LengthUnit.MILLIMETER, 28.0, LengthUnit.MILLIMETER);

    public static final Wheel EV3Rim = new WheelImpl(30.0, LengthUnit.MILLIMETER, 20, LengthUnit.MILLIMETER);

    public static final Wheel EV3Tire = new WheelImpl(43.2, LengthUnit.MILLIMETER, 21.0, LengthUnit.MILLIMETER);

}
