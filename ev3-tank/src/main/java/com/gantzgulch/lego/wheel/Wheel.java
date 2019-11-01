package com.gantzgulch.lego.wheel;

import com.gantzgulch.lego.units.LengthUnit;
import com.gantzgulch.lego.wheel.impl.WheelImpl;

public interface Wheel {

    double getWidthMMs();
    
    double getDiameterMMs();
    
    double getCircumferenceMMs();
    
    static Wheel customWheel(final double diameter, final LengthUnit diameterUnit, final double width, final LengthUnit widthUnit) {
        return new WheelImpl(diameter, diameterUnit, width, widthUnit);
    }
}
