package com.gantzgulch.lego.unit;

public class SpeedDPM extends SpeedRPS {

    public SpeedDPM(final double degreesPerMinute) {
        super(   degreesPerMinute / 360.0 / 60.0 );
    }

}
