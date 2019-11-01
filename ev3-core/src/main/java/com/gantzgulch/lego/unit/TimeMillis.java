package com.gantzgulch.lego.unit;

public class TimeMillis implements Time {

    protected final long millis;
    
    public TimeMillis(final long millis) {
        this.millis = millis;
    }

    @Override
    public long toMillis() {
        return 0;
    }
}
