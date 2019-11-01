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
