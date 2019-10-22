package com.gantzgulch.lego.unit;

public class SpeedPercent implements Speed {

    private final double percent;

    public SpeedPercent(final double percent) {
        this.percent = percent;
    }

    @Override
    public double rotationsPerSecond(final double maxRotationsPerSecond) {
        return percent / 100.0 * maxRotationsPerSecond;
    }
}
