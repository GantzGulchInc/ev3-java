package com.gantzgulch.lego.units;

public enum LengthUnit {

    INCH(LengthUnit.INCH_SCALE),
    FOOT(LengthUnit.FOOT_SCALE),
    METER(LengthUnit.METER_SCALE),
    CENTIMETER(LengthUnit.CENTIMETER_SCALE),
    MILLIMETER(LengthUnit.MILLIMETER_SCALE);
    
    private static final double INCH_SCALE   = 25.4;
    private static final double FOOT_SCALE   = 304.8;
    private static final double METER_SCALE = 0.001;
    private static final double CENTIMETER_SCALE = 0.1;
    private static final double MILLIMETER_SCALE = 1;
    
    
    private final double scale;
    
    private LengthUnit(final double scale) {
        this.scale = scale;
    }
    
    public double toMilliMeters(final double lengthUnit) {
        return lengthUnit * scale;
    }
}
