package com.gantzgulch.lego.unit;

import java.util.concurrent.TimeUnit;

public class TimeSeconds implements Time {

    protected final long millis;
    
    public TimeSeconds(final long seconds) {
        this.millis = TimeUnit.SECONDS.toMillis(seconds);
    }

    @Override
    public long toMillis() {
        return 0;
    }
}
