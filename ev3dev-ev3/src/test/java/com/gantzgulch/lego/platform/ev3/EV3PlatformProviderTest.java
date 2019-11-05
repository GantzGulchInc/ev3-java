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
package com.gantzgulch.lego.platform.ev3;

import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.ServiceLoader;

import org.junit.Test;

import com.gantzgulch.lego.common.logger.EV3Logger;
import com.gantzgulch.lego.ev3dev.ev3.EV3PlatformProvider;
import com.gantzgulch.lego.ev3dev.platform.PlatformProvider;

public class EV3PlatformProviderTest {

    private static final EV3Logger LOG = EV3Logger.getLogger(EV3PlatformProviderTest.class);
    
    @Test
    public void testEV3PlatformProviderCanBeFound() {

        final ServiceLoader<PlatformProvider> sp = ServiceLoader.load(PlatformProvider.class);

        final PlatformProvider pp = sp//
                .stream() //
                .filter(p -> {
                    LOG.fine("platform: %s", p.get().getName());
                    return p.get().getName().contentEquals("EV3");
                }) //
                .findFirst() //
                .get() //
                .get();

        assertThat(pp, notNullValue());
        assertThat(pp, isA(EV3PlatformProvider.class));
    }
}
