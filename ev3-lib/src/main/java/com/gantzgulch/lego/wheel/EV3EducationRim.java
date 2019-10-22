package com.gantzgulch.lego.wheel;

import com.gantzgulch.lego.units.LengthUnit;
import com.gantzgulch.lego.wheel.impl.WheelImpl;

public class EV3EducationRim extends WheelImpl {

    public static final EV3EducationRim INSTANCE = new EV3EducationRim();
    
    public EV3EducationRim() {
        super(43.0, LengthUnit.MILLIMETER, 26.0, LengthUnit.MILLIMETER);
    }

}
