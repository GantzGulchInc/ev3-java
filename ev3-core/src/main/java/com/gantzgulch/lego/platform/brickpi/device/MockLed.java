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
package com.gantzgulch.lego.platform.brickpi.device;

import java.time.Duration;
import java.util.Optional;

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
