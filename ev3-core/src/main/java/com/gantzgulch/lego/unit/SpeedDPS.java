package com.gantzgulch.lego.unit;

public class SpeedDPS extends SpeedRPS {

    public SpeedDPS(final double degreesPerSecond) {
        super(   degreesPerSecond / 360.0 );
    }

    @Override
    public String toString() {
        return "Speed:" + Double.toString(rps * 360.0 ) + "dps";
    }
}
