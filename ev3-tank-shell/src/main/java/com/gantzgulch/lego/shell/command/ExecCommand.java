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

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import com.gantzgulch.lego.shell.EV3Shell;
import com.gantzgulch.lego.tank.Tank;

public class ExecCommand extends AbstractCommand {

    public ExecCommand(final String[] args) {
        super(true, args);
    }

    @Override
    public void execute(final EV3Shell shell, final Tank tank) {

        if (args.length < 2) {
            throw new IllegalArgumentException("Invalid argument count.");
        }

        final Path filePath = Path.of(args[1]);

        try {

            Files //
                    .lines(filePath, StandardCharsets.UTF_8) //
                    .forEach(line -> {
                        shell.execute(line);
                    });

        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String help() {
        return "exec:help";
    }

    @Override
    public String toString() {
        return String.format("%s %s", args[0], args[1]);
    }

    public static final ExecCommand parse(final String[] args) {
        return new ExecCommand(args);
    }
}
