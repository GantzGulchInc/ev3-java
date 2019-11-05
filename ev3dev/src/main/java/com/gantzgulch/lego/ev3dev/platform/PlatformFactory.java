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
package com.gantzgulch.lego.ev3dev.platform;

import java.util.ServiceLoader;

import com.gantzgulch.lego.api.exception.PlatformRuntimeException;
import com.gantzgulch.lego.api.platform.Platform;

public class PlatformFactory {

    public static final PlatformFactory INSTANCE = new PlatformFactory();

    private Platform platformInstance = null;

    private PlatformFactory() {

    }

    public synchronized Platform getPlatform() {

        if (platformInstance == null) {
            platformInstance = createPlatform();
        }

        return platformInstance;
    }

    private Platform createPlatform() {

        final ServiceLoader<PlatformProvider> loader = ServiceLoader.load(PlatformProvider.class);

        return loader //
                .stream() //
                .filter(p -> {
                    return p.get().isPlatform();
                }) //
                .findFirst() //
                .map(l -> {
                    return l.get().getInstance();
                }) //
                .orElseThrow(() -> new PlatformRuntimeException("No platform driver found."));

    }

}
