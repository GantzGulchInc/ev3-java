/*******************************************************************************
 *    Copyright 2019 GantzGulch, Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *******************************************************************************/
package com.gantzgulch.lego.ev3dev.device.ev3;

import com.gantzgulch.lego.api.device.ev3.EV3LedTrigger;
import com.gantzgulch.lego.common.lang.BidirectionalEnumMap;

public class EV3LedTriggerMap extends BidirectionalEnumMap<EV3LedTrigger> {

    public static final EV3LedTriggerMap INSTANCE = new EV3LedTriggerMap();

    public EV3LedTriggerMap() {

        super(EV3LedTrigger.values());

        add(EV3LedTrigger.NONE, "none");

        add(EV3LedTrigger.RFKILL_ANY, "rfkill-any");
        add(EV3LedTrigger.KBD_SCROLLLOCK, "kbd-scrolllock");
        add(EV3LedTrigger.KBD_NUMLOCK, "kbd-numlock");
        add(EV3LedTrigger.KBD_CAPSLOCK, "kbd-capslock");
        add(EV3LedTrigger.KBD_KANALOCK, "kbd-kanalock");
        add(EV3LedTrigger.KBD_SHIFTLOCK, "kbd-shiftlock");
        add(EV3LedTrigger.KBD_ALTGRLOCK, "kbd-altgrlock");
        add(EV3LedTrigger.KBD_CTRLLOCK, "kbd_ctrllock");
        add(EV3LedTrigger.KBD_ALTLOCK, "kbd-altlock");
        add(EV3LedTrigger.KBD_SHIFTLLOCK, "kbd-shiftllock");
        add(EV3LedTrigger.KBD_SHIFTRLOCK, "kbd-shiftrlock");
        add(EV3LedTrigger.KBD_CTRLLLOCK, "kbd-ctrlllock");
        add(EV3LedTrigger.KBD_CTRLRLOCK, "kbd-ctrlrlock");
        add(EV3LedTrigger.MMC0, "mmc0");
        add(EV3LedTrigger.TIMER, "timer");
        add(EV3LedTrigger.HEARTBEAT, "heartbeat");
        add(EV3LedTrigger.DEFAULT_ON, "default-on");
        add(EV3LedTrigger.TRANSIENT, "transient");
        add(EV3LedTrigger.LEGO_EV3_BATTERY_FULL, "lego-ev3-battery-full");
        add(EV3LedTrigger.LEGO_EV3_BATTERY_CHARGING_BLINK_FULL_SOLID, "lego-ev3-battery-charging-blink-full-solid");
        add(EV3LedTrigger.RFKILL0, "rfkill0");
    }

}