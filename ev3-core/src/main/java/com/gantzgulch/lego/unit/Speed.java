package com.gantzgulch.lego.unit;

public interface Speed {

    double rotationsPerSecond(double maxRotationsPerSecond);
    
    public static Speed parse(final String value) {
        return SpeedParser.parse(value).orElseThrow( () -> new IllegalArgumentException("Unable to parse speed: " + value) );
    }
}
