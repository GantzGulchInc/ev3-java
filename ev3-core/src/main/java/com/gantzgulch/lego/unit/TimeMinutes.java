package com.gantzgulch.lego.unit;

import java.util.concurrent.TimeUnit;

public class TimeMinutes implements Time {

    protected final long millis;
    
    public TimeMinutes(final long minutes) {
        this.millis = TimeUnit.MINUTES.toMillis(minutes);
    }

    @Override
    public long toMillis() {
        return 0;
    }
}
