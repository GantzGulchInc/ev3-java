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
