package com.gantzgulch.lego.platform.ev3.device;

import java.nio.file.Path;
import java.time.Duration;
import java.util.Optional;

import com.gantzgulch.lego.device.ev3.EV3Led;
import com.gantzgulch.lego.platform.common.Attribute;
import com.gantzgulch.lego.platform.common.AttributeType;
import com.gantzgulch.lego.util.lang.BidirectionalEnumMap;

public class EV3LedImpl implements EV3Led {

    public static final String ATTR_MAX_BRIGHTNESS = "max_brightness";
    public static final String ATTR_BRIGHTNESS = "brightness";
    public static final String ATTR_DELAY_ON = "delay_on";
    public static final String ATTR_DELAY_OFF = "delay_off";
    public static final String ATTR_TRIGGER = "trigger";

    private final Path sysPath;

    private final BidirectionalEnumMap<LedTrigger> triggerMap;

    private final Attribute maxBrightness;

    private final Attribute brightness;

    private final Attribute delayOn;

    private final Attribute delayOff;

    private final Attribute trigger;

    public EV3LedImpl(final Path sysPath, final BidirectionalEnumMap<LedTrigger> triggerMap) {

        this.sysPath = sysPath;
        this.triggerMap = triggerMap;

        this.maxBrightness = new Attribute(AttributeType.READ_ONLY, false, this.sysPath, ATTR_MAX_BRIGHTNESS);
        this.brightness = new Attribute(AttributeType.READ_WRITE, false, this.sysPath, ATTR_BRIGHTNESS);
        this.delayOn = new Attribute(AttributeType.READ_WRITE, false, this.sysPath, ATTR_DELAY_ON);
        this.delayOff = new Attribute(AttributeType.READ_WRITE, false, this.sysPath, ATTR_DELAY_OFF);
        this.trigger = new Attribute(AttributeType.READ_WRITE, false, this.sysPath, ATTR_TRIGGER);
    }

    @Override
    public int getMaxBrightness() {
        return maxBrightness.readInteger().orElse(0);
    }
    
    @Override
    public void setBrightnessPercent(final double percent) {
        
        final double normalPercent = Math.min(100, Math.max(0, percent) );
        
        final int level = (int)(normalPercent / 100.0 * getMaxBrightness());
        
        setBrightness(level);
        
    }

    @Override
    public void setBrightness(final int newBrightness) {
        brightness.writeInteger(newBrightness);
    }

    @Override
    public int getBrightness() {
        return brightness.readInteger().orElse(0);
    }

    @Override
    public void setDelayOn(final Duration duration) {
        delayOn.writeInteger((int) duration.toMillis());
    }

    @Override
    public int getDelayOn() {
        return delayOn.readInteger().orElse(0);
    }

    @Override
    public void setDelayOff(final Duration duration) {
        delayOff.writeInteger((int) duration.toMillis());
    }

    @Override
    public int getDelayOff() {
        return delayOff.readInteger().orElse(0);
    }

    @Override
    public Optional<LedTrigger> getTrigger() {

        for (final String t : trigger.readStringArray()) {

            if (t.startsWith("[") && t.endsWith("]")) {

                return triggerMap.get(t.substring(0, t.length() - 1));
            }

        }

        return Optional.empty();
    }

    @Override
    public void setTrigger(final LedTrigger newTrigger) {
        trigger.writeEnum(newTrigger, triggerMap);
    }
}
