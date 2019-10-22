package com.gantzgulch.lego.wheel;

import com.gantzgulch.lego.units.LengthUnit;
import com.gantzgulch.lego.wheel.impl.WheelImpl;

public class EV3EducationTire extends WheelImpl {

    public static final EV3EducationTire INSTANCE = new EV3EducationTire();
    
    public EV3EducationTire() {
        super(56.0, LengthUnit.MILLIMETER, 28.0, LengthUnit.MILLIMETER);
    }

}
