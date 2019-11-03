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

import com.gantzgulch.lego.units.LengthUnit;
import com.gantzgulch.lego.wheel.Wheel;

public class WheelImpl implements Wheel{

    private final double diameter;
    
    private final double circumference;
    
    private final double width;
    
    public WheelImpl(final double diameter, final LengthUnit diameterUnit, final double width, final LengthUnit widthUnit) {
        this.diameter = diameterUnit.toMilliMeters(diameter);
        this.circumference = this.diameter * Math.PI;
        this.width = widthUnit.toMilliMeters(width);
    }

    @Override
    public double getWidthMMs() {
        return width;
    }
    
    @Override
    public double getDiameterMMs() {
        return diameter;
    }

    @Override
    public double getCircumferenceMMs() {
        return circumference;
    }

    
}
