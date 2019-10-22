package com.gantzgulch.lego.wheel;

import com.gantzgulch.lego.units.LengthUnit;
import com.gantzgulch.lego.wheel.impl.WheelImpl;

public class EV3Rim extends WheelImpl {

    public static final EV3Rim INSTANCE = new EV3Rim();
    
    public EV3Rim() {
        super(30.0, LengthUnit.MILLIMETER, 20, LengthUnit.MILLIMETER);
    }

}
