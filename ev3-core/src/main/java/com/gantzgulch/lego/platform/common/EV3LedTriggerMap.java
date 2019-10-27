package com.gantzgulch.lego.platform.common;

import com.gantzgulch.lego.device.ev3.EV3Led.LedTrigger;
import com.gantzgulch.lego.util.lang.BidirectionalEnumMap;

public class EV3LedTriggerMap extends BidirectionalEnumMap<LedTrigger> {

    public static final EV3LedTriggerMap INSTANCE = new EV3LedTriggerMap();

    public EV3LedTriggerMap() {

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