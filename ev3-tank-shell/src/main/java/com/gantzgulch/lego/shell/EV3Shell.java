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
package com.gantzgulch.lego.shell;

import java.util.Scanner;

import com.gantzgulch.lego.shell.record.Recorder;
import com.gantzgulch.lego.tank.Tank;

public class EV3Shell implements Runnable {

    private final Tank tank;

    private final CommandParser parser;

    private final Recorder recorder;
    
    private boolean isRunning = true;
    
    public EV3Shell(final Tank tank) {
        this.tank = tank;
        this.parser = new CommandParser();
        this.recorder = new Recorder(this, this.tank);
    }

    public void run() {

        try (final Scanner scanner = new Scanner(System.in)) {

            while (isRunning) {

                System.out.print("ev3>");
                System.out.flush();

                final String line = scanner.nextLine();

                execute(line);
            }
            
        }
    };
    
    public void execute(final String line) {
        
        Command command = null;
        
        try {

            command = parser.parse(line);

            command.execute(this, tank);
            
            recorder.record(command);

        } catch (final RuntimeException e) {
            
            e.printStackTrace(System.out);
            
            System.out.flush();
            
            if( command != null ) {
                System.out.println("\n" + command.help() );
            }
            
        }
}
    
    
    public void stop() {
        isRunning = false;
    }
    
    public Recorder getRecorder() {
        return recorder;
    }
}
