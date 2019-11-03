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
package com.gantzgulch.lego.shell.command;

import java.util.Arrays;
import java.util.Objects;

import com.gantzgulch.lego.shell.EV3Shell;
import com.gantzgulch.lego.tank.Tank;

public class RecorderCommand extends AbstractCommand {

    private RecorderCommands cmd;

    public RecorderCommand(final String[] args) {
        super(false, args);
    }

    @Override
    public void execute(final EV3Shell shell, final Tank tank) {

        if (args.length < 2) {
            throw new IllegalArgumentException("Invalid argument count.");
        }

        cmd = RecorderCommands.parse(args[1]);

        switch (cmd) {

        case ON:
            shell.getRecorder().setRecording(true);
            break;

        case OFF:
            shell.getRecorder().setRecording(false);
            break;

        case REPLAY:
            shell.getRecorder().replay();
            break;
            
        case LIST:
            shell.getRecorder().list();
            
        case CLEAR:
            shell.getRecorder().clear();
            break;
        }
    }

    @Override
    public String help() {
        return "setRampDown:help";
    }

    public static final RecorderCommand parse(final String[] args) {
        return new RecorderCommand(args);
    }

    public static enum RecorderCommands {

        ON("on"), OFF("off"), REPLAY("replay"), LIST("list"), CLEAR("clear");

        private final String value;

        private RecorderCommands(final String value) {
            this.value = value;
        }

        public static RecorderCommands parse(final String value) {

            return Arrays //
                    .stream(values()) //
                    .filter(e -> {
                        return Objects.equals(e.value, value);
                    }) //
                    .findFirst() //
                    .orElseThrow(() -> {
                        return new IllegalArgumentException("Unknown recorder command: " + value);
                    });

        }
    }
}
