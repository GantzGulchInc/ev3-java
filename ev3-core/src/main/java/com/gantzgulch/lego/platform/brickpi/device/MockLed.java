package com.gantzgulch.lego.platform.brickpi.device;

import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import com.gantzgulch.lego.device.ev3.EV3Led;

public class MockLed implements EV3Led {

    private int brightness;

    private int delayOn;

    private int delayOff;

    private LedTrigger trigger;

    public MockLed() {
    }

    @Override
    public int getMaxBrightness() {
        return 100;
    }

    @Override
    public int getBrightness() {
        return brightness;
    }

    @Override
    public void setBrightness(final int brightness) {
        this.brightness = brightness;
    }

    @Override
    public void setBrightnessPercent(final double percent) {

        final double normalPercent = Math.min(100, Math.max(0, percent));

        final int level = (int) (normalPercent / 100.0 * getMaxBrightness());

        setBrightness(level);
    }

    @Override
    public void setDelayOn(final Duration duration) {
        this.delayOn = (int) duration.toMillis();
    }
    
    @Override
    public int getDelayOn() {
        return delayOn;
    }

    @Override
    public void setDelayOff(final Duration duration) {
        this.delayOff = (int) duration.toMillis();
    }

    @Override
    public int getDelayOff() {
        return delayOff;
    }

    @Override
    public Optional<LedTrigger> getTrigger() {
        return Optional.ofNullable(trigger);
    }

    @Override
    public void setTrigger(final LedTrigger trigger) {
        this.trigger = trigger;
    }

}
