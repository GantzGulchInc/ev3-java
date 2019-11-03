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
package com.gantzgulch.lego.shell.record;

import java.util.ArrayList;
import java.util.List;

import com.gantzgulch.lego.shell.Command;
import com.gantzgulch.lego.shell.EV3Shell;
import com.gantzgulch.lego.tank.Tank;

public class Recorder {

    private final EV3Shell shell;

    private final Tank tank;

    private final List<Command> commands = new ArrayList<>();

    private boolean isRecording = false;

    
    public Recorder(final EV3Shell shell, final Tank tank) {
        this.shell = shell;
        this.tank = tank;
    }

    public boolean isRecording() {
        return isRecording;
    }
    
    public void setRecording(final boolean isRecording) {
        this.isRecording = isRecording;
    }
    
    public void clear() {
        commands.clear();
    }

    public void record(final Command command) {
        
        if( isRecording && command.isRecordable() ) {
            commands.add(command);
        }
        
    }
    
    public void replay() {

        commands //
        .stream() //
        .forEachOrdered( c -> {
            c.execute(shell, tank);
        });
        
    }
    
    public void list() {
        
        commands //
        .stream() //
        .forEachOrdered( c -> {
            
            System.out.println("cmd: " + c);
            
        });
        
        
        
    }
    
}
