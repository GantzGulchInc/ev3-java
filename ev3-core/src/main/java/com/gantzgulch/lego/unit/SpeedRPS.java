package com.gantzgulch.lego.unit;

public class SpeedRPS implements Speed {

    private final double rps;

    public SpeedRPS(final double rotationsPerSecond) {
        this.rps = rotationsPerSecond;
    }

    @Override
    public double rotationsPerSecond(final double maxRotationsPerSecond) {
        
        if( Math.abs(rps) > maxRotationsPerSecond ) {
            return maxRotationsPerSecond * Math.signum(rps);
        }
        
        return rps;
    }
}
