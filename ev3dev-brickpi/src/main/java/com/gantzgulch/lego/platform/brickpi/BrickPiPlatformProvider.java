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
package com.gantzgulch.lego.platform.brickpi;

import java.io.IOException;
import java.nio.file.Path;

import com.gantzgulch.lego.api.device.Board;
import com.gantzgulch.lego.api.platform.Platform;
import com.gantzgulch.lego.common.logger.EV3Logger;
import com.gantzgulch.lego.ev3dev.device.BoardImpl;
import com.gantzgulch.lego.ev3dev.platform.PlatformProvider;

public class BrickPiPlatformProvider implements PlatformProvider {

    public static final String PLATFORM = "BrickPi";
    
    public static final String RASPBERRY_PI_MODEL_REGEX = "\\s*Raspberry\\s*Pi.*";

    public static final String BRICKPI3_MODEL_REGEX = ".*BrickPi3.*";

    private static final EV3Logger LOG = EV3Logger.getLogger(BrickPiPlatformProvider.class);

    private Platform instance = null;

    public BrickPiPlatformProvider() {
    }

    @Override
    public String getName() {
        return PLATFORM;
    }
    
    @Override
    public boolean isPlatform() {

        LOG.fine("isPlatform: checking...");
        
        try (final Board board0 = new BoardImpl(Path.of("/sys/class/board-info/board0"));
                final Board board1 = new BoardImpl(Path.of("/sys/class/board-info/board1"))) {

            return board0.existsAndIsModel(RASPBERRY_PI_MODEL_REGEX)

                    && board1.existsAndIsModel(BRICKPI3_MODEL_REGEX);

        } catch (final RuntimeException | IOException e) {

            return false;

        }

    }

    @Override
    public Platform getInstance() {

        if (instance == null) {
            instance = new BrickPiPlatform();
        }

        return instance;
    }

}
