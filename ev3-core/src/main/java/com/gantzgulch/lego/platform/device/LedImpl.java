package com.gantzgulch.lego.platform.device;

import java.nio.file.Path;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import com.gantzgulch.lego.device.led.Led;
import com.gantzgulch.lego.platform.impl.Attribute;
import com.gantzgulch.lego.platform.impl.AttributeType;
import com.gantzgulch.lego.util.BidirectionalEnumMap;

public class LedImpl implements Led {

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

    public LedImpl(final Path sysPath, final BidirectionalEnumMap<LedTrigger> triggerMap) {

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
    public void setBrightness(final int newBrightness) {
        brightness.writeInteger(newBrightness);
    }

    @Override
    public int getBrightness() {
        return brightness.readInteger().orElse(0);
    }

    @Override
    public void setDelayOn(long timeUnit, TimeUnit unit) {
        delayOn.writeInteger((int) unit.toMillis(timeUnit));
    }

    @Override
    public int getDelayOn() {
        return delayOn.readInteger().orElse(0);
    }

    @Override
    public void setDelayOff(long timeUnit, TimeUnit unit) {
        delayOff.writeInteger((int) unit.toMillis(timeUnit));
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

    public static class LedTriggerMap extends BidirectionalEnumMap<LedTrigger> {

        public static final LedTriggerMap INSTANCE = new LedTriggerMap();

        public LedTriggerMap() {

            super(LedTrigger.values());

            add(LedTrigger.NONE, "none");

            add(LedTrigger.RFKILL_ANY, "rfkill-any");
            add(LedTrigger.KBD_SCROLLLOCK, "kbd-scrolllock");
            add(LedTrigger.KBD_NUMLOCK, "kbd-numlock");
            add(LedTrigger.KBD_CAPSLOCK, "kbd-capslock");
            add(LedTrigger.KBD_KANALOCK, "kbd-kanalock");
            add(LedTrigger.KBD_SHIFTLOCK, "kbd-shiftlock");
            add(LedTrigger.KBD_ALTGRLOCK, "kbd-altgrlock");
            add(LedTrigger.KBD_CTRLLOCK, "kbd_ctrllock");
            add(LedTrigger.KBD_ALTLOCK, "kbd-altlock");
            add(LedTrigger.KBD_SHIFTLLOCK, "kbd-shiftllock");
            add(LedTrigger.KBD_SHIFTRLOCK, "kbd-shiftrlock");
            add(LedTrigger.KBD_CTRLLLOCK, "kbd-ctrlllock");
            add(LedTrigger.KBD_CTRLRLOCK, "kbd-ctrlrlock");
            add(LedTrigger.MMC0, "mmc0");
            add(LedTrigger.TIMER, "timer");
            add(LedTrigger.HEARTBEAT, "heartbeat");
            add(LedTrigger.DEFAULT_ON, "default-on");
            add(LedTrigger.TRANSIENT, "transient");
            add(LedTrigger.LEGO_EV3_BATTERY_FULL, "lego-ev3-battery-full");
            add(LedTrigger.LEGO_EV3_BATTERY_CHARGING_BLINK_FULL_SOLID, "lego-ev3-battery-charging-blink-full-solid");
            add(LedTrigger.RFKILL0, "rfkill0");
        }

    }
}
