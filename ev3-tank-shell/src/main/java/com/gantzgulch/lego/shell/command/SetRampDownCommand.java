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

import java.time.Duration;

import com.gantzgulch.lego.shell.EV3Shell;
import com.gantzgulch.lego.tank.Tank;
import com.gantzgulch.lego.unit.DurationParser;

public class SetRampDownCommand extends AbstractCommand {

    private Duration duration;

    public SetRampDownCommand(final String[] args) {
        super(true, args);
    }

    @Override
    public void execute(final EV3Shell shell, final Tank tank) {
        
        if( args.length < 2 ) {
            throw new IllegalArgumentException("Invalid argument count.");
        }

        for(int i=0; i<args.length; i++) {
            
            final String arg = args[i];
            
            LOG.finest("execute: Parsing: [%d]:%s", i, arg);
            
            switch(i) {
            
            case 1:
                duration = DurationParser.parse(arg);
                break;
                
            }
            
        }

        tank.setRampDown(duration);
        
    }

    @Override
    public String help() {
        return "setRampDown:help";
    }

    
    @Override
    public String toString() {
        return String.format("%s %dms", args[0], duration.toMillis());
    }
    
    public static final SetRampDownCommand parse(final String[] args) {
        return new SetRampDownCommand(args);
    }
}
