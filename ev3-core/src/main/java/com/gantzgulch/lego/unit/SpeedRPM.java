package com.gantzgulch.lego.unit;

public class SpeedRPM extends SpeedRPS {

    public SpeedRPM(final double rotationsPerMinute) {
        super( rotationsPerMinute / 60.0 );
    }



    @Override
    public String toString() {
        return "Speed:" + Double.toString(rps * 60.0) + "rpm";
    }
}
