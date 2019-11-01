package com.gantzgulch.lego.unit;

public class SpeedRPS implements Speed {

    protected final double rps;

    public SpeedRPS(final double rotationsPerSecond) {
        this.rps = rotationsPerSecond;
    }

    @Override
    public double rotationsPerSecond(final double maxRotationsPerSecond) {

        if (Math.abs(rps) > maxRotationsPerSecond) {
            return maxRotationsPerSecond * Math.signum(rps);
        }

        return rps;
    }

    public double getRps() {
        return rps;
    }

    @Override
    public String toString() {
        return "Speed:" + Double.toString(rps) + "rps";
    }

}
