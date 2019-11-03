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

import com.gantzgulch.lego.shell.EV3Shell;
import com.gantzgulch.lego.tank.Tank;
import com.gantzgulch.lego.unit.Speed;

public class OnForRotationsCommand extends AbstractCommand {

    private Speed leftSpeed;
    
    private Speed rightSpeed;
    
    private double rotations;
    
    private boolean brake = true;
    
    private boolean wait = true;

    public OnForRotationsCommand(final String[] args) {
        super(true, args);
    }

    @Override
    public void execute(final EV3Shell shell, final Tank tank) {
        
        if( args.length < 4 ) {
            throw new IllegalArgumentException("Invalid argument count.");
        }

        for(int i=0; i<args.length; i++) {
            
            final String arg = args[i];
            
            LOG.finest("execute: Parsing: [%d]:%s", i, arg);
            
            switch(i) {
            case 1:
                leftSpeed = Speed.parse(arg);
                break;
                
            case 2: 
                rightSpeed = Speed.parse(arg);
                break;
            
            case 3:
                rotations = Double.parseDouble(arg);
                break;
              
            case 4:
                brake = Boolean.parseBoolean(arg);
                break;
                
            case 5:
                wait = Boolean.parseBoolean(arg);
            }
            
        }

        tank.onForRotations(leftSpeed, rightSpeed, rotations, brake, wait);
        
    }

    @Override
    public String help() {
        return "onForRotations:help";
    }
    
    @Override
    public String toString() {
        return String.format("%s %s %s %f %b %b", args[0], leftSpeed, rightSpeed, rotations, brake, wait);
    }
    
    public static final OnForRotationsCommand parse(final String[] args) {
        return new OnForRotationsCommand(args);
    }
}
