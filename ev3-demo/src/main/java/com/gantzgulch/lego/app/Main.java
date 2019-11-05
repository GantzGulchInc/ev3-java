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
package com.gantzgulch.lego.app;

import java.io.IOException;

import com.gantzgulch.lego.api.platform.Platform;
import com.gantzgulch.lego.common.logger.EV3Logger;
import com.gantzgulch.lego.ev3dev.platform.PlatformFactory;

public class Main {

    private static final EV3Logger LOG = EV3Logger.getLogger(Main.class);

    private static void warmUp() {
    
        
        try(final WarmUp warmUp = new WarmUp()) {
            
            warmUp.run();
            
        }

    }

    public static void main(final String[] args) {

        LOG.info("Running...");

        warmUp();
        
        try(final Platform platform = PlatformFactory.INSTANCE.getPlatform() ) {

            final ShellDemo demo = new ShellDemo(platform);

            demo.run();

        } catch(final IOException e) {
            e.printStackTrace();
        }

    }

}
