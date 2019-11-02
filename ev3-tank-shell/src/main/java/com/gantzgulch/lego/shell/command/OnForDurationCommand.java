package com.gantzgulch.lego.shell.command;

import java.time.Duration;

import com.gantzgulch.lego.shell.EV3Shell;
import com.gantzgulch.lego.tank.Tank;
import com.gantzgulch.lego.unit.DurationParser;
import com.gantzgulch.lego.unit.Speed;

public class OnForDurationCommand extends AbstractCommand {

    private Speed leftSpeed;
    
    private Speed rightSpeed;
    
    private Duration duration;
    
    private boolean brake = true;
    
    private boolean wait = true;

    public OnForDurationCommand(final String[] args) {
        super(args);
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
                duration = DurationParser.parse(arg);
                break;
              
            case 4:
                brake = Boolean.parseBoolean(arg);
                break;
                
            case 5:
                wait = Boolean.parseBoolean(arg);
            }
            
        }

        tank.onForDuration(leftSpeed, rightSpeed, duration, brake, wait);
        
    }

    @Override
    public String help() {
        return "onForDuration:help";
    }

    public static final OnForDurationCommand parse(final String[] args) {
        return new OnForDurationCommand(args);
    }
}
