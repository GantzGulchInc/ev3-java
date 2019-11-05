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
package com.gantzgulch.lego.wheel.impl;

import com.gantzgulch.lego.api.unit.Length;
import com.gantzgulch.lego.wheel.Wheel;

public class WheelImpl implements Wheel {

    private final Length diameter;

    private final Length circumference;

    private final Length width;

    public WheelImpl(final Length diameter, final Length width) {
        this.diameter = diameter;
        this.circumference = Length.ofMillimeters(this.diameter.toMillimeters() * Math.PI);
        this.width = width;
    }

    @Override
    public double getWidthMMs() {
        return width.toMillimeters();
    }

    @Override
    public double getDiameterMMs() {
        return diameter.toMillimeters();
    }

    @Override
    public double getCircumferenceMMs() {
        return circumference.toMillimeters();
    }

}
