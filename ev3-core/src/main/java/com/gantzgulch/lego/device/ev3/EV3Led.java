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
package com.gantzgulch.lego.device.ev3;

import java.time.Duration;
import java.util.Optional;

public interface EV3Led {

    int getMaxBrightness();

    int getBrightness();

    void setBrightness(int brightness);
    
    void setBrightnessPercent(double percent);

    void setDelayOn(Duration duration);

    int getDelayOn();

    void setDelayOff(Duration duration);

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
    
    public static enum LedColor {
        
        GREEN, //
        RED;
    }
}
