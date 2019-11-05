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
package com.gantzgulch.lego.common.lang;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import com.gantzgulch.lego.common.logger.EV3Logger;

public class Sleep {

    private static final EV3Logger LOG = EV3Logger.getLogger(Sleep.class);

    private Sleep() {
    }

    public static void sleep(final long millis) {

        try {

            Thread.sleep(millis);

        } catch (final InterruptedException e) {
            LOG.warning(e, "Sleep was interrupted.");
        }
    }

    public static void sleep(final long timeUnit, final TimeUnit unit) {
        sleep(unit.toMillis(timeUnit));
    }

    public static void sleep(final Duration duration) {
        sleep(duration.toMillis());
    }
}
