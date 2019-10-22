package com.gantzgulch.lego.unit;

public class SpeedRPM extends SpeedRPS {

    public SpeedRPM(final double rotationsPerMinute) {
        super( rotationsPerMinute / 60.0 );
    }

}
