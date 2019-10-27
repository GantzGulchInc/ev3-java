package com.gantzgulch.lego.device.ev3;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public interface EV3Led {

    int getMaxBrightness();

    int getBrightness();

    void setBrightness(int brightness);

    void setDelayOn(long timeUnit, final TimeUnit unit);

    int getDelayOn();

    void setDelayOff(long timeUnit, final TimeUnit unit);

    int getDelayOff();

    Optional<LedTrigger> getTrigger();

    void setTrigger(LedTrigger trigger);

    public static enum LedTrigger {

        NONE, //
        RFKILL_ANY, //
        KBD_SCROLLLOCK, //
        KBD_NUMLOCK, //
        KBD_CAPSLOCK, //
        KBD_KANALOCK, //
        KBD_SHIFTLOCK, //
        KBD_ALTGRLOCK, //
        KBD_CTRLLOCK, //
        KBD_ALTLOCK, //
        KBD_SHIFTLLOCK, //
        KBD_SHIFTRLOCK, //
        KBD_CTRLLLOCK, //
        KBD_CTRLRLOCK, //
        MMC0, //
        TIMER, HEARTBEAT, //
        DEFAULT_ON, TRANSIENT, //
        LEGO_EV3_BATTERY_FULL, //
        LEGO_EV3_BATTERY_CHARGING_BLINK_FULL_SOLID, //
        RFKILL0;

    }
}
