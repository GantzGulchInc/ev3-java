package com.gantzgulch.lego.wheel;

import com.gantzgulch.lego.units.LengthUnit;
import com.gantzgulch.lego.wheel.impl.WheelImpl;

public class EV3Tire extends WheelImpl {

    public static final EV3Tire INSTANCE = new EV3Tire();
    
    public EV3Tire() {
        super(43.2, LengthUnit.MILLIMETER, 21.0, LengthUnit.MILLIMETER);
    }

}
