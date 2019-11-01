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
