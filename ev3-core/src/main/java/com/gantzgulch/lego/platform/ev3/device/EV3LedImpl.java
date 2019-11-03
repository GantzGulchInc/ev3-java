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
package com.gantzgulch.lego.platform.ev3.device;

import static com.gantzgulch.lego.platform.common.AttributeFactory.createAttribute;

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

        this.maxBrightness = createAttribute(AttributeType.READ_ONLY, false, this.sysPath, ATTR_MAX_BRIGHTNESS);
        this.brightness = createAttribute(AttributeType.READ_WRITE, false, this.sysPath, ATTR_BRIGHTNESS);
        this.delayOn = createAttribute(AttributeType.READ_WRITE, false, this.sysPath, ATTR_DELAY_ON);
        this.delayOff = createAttribute(AttributeType.READ_WRITE, false, this.sysPath, ATTR_DELAY_OFF);
        this.trigger = createAttribute(AttributeType.READ_WRITE, false, this.sysPath, ATTR_TRIGGER);
    }

    @Override
    public int getMaxBrightness() {
        return maxBrightness.readInteger().orElse(0);
    }

    @Override
    public void setBrightnessPercent(final double percent) {

        final double normalPercent = Math.min(100, Math.max(0, percent));

        final int level = (int) (normalPercent / 100.0 * getMaxBrightness());

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
