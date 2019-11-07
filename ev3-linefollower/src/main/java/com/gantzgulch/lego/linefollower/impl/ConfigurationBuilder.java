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
package com.gantzgulch.lego.linefollower.impl;

import com.gantzgulch.lego.api.unit.Speed;
import com.gantzgulch.lego.api.unit.SpeedPercent;
import com.gantzgulch.lego.linefollower.LineFollowerConfiguration;

public class ConfigurationBuilder {

    private Speed speed = new SpeedPercent(20.0);

    private int target = 40;

    private int pidWidth = 30;

    private double kp = 1.0;

    private double ki = 0.0;

    private double kd = 0.0;

    public ConfigurationBuilder() {

    }

    public ConfigurationBuilder withSpeed(final Speed speed) {
        this.speed = speed;
        return this;
    }

    public ConfigurationBuilder withTarget(final int target) {
        this.target = target;
        return this;
    }

    public ConfigurationBuilder withPidWidth(final int pidWidth) {
        this.pidWidth = pidWidth;
        return this;
    }

    public ConfigurationBuilder withKp(final double kp) {
        this.kp = kp;
        return this;
    }

    public ConfigurationBuilder withKi(final double ki) {
        this.ki = ki;
        return this;
    }

    public ConfigurationBuilder withkd(final double kd) {
        this.kd = kd;
        return this;
    }

    public LineFollowerConfiguration build() {
        return new LFC();
    }

    private class LFC implements LineFollowerConfiguration {

        @Override
        public Speed getSpeed() {
            return speed;
        }

        @Override
        public int getTargetColor() {
            return target;
        }

        @Override
        public int getPidWidth() {
            return pidWidth;
        }

        @Override
        public double getKp() {
            return kp;
        }

        @Override
        public double getKi() {
            return ki;
        }

        @Override
        public double getKd() {
            return kd;
        }
    }
}
