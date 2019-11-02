package com.gantzgulch.lego.shell.command;

import com.gantzgulch.lego.shell.EV3Shell;
import com.gantzgulch.lego.tank.Tank;
import com.gantzgulch.lego.unit.Speed;

public class OnForDegreesCommand extends AbstractCommand {

    private Speed leftSpeed;
    
    private Speed rightSpeed;
    
    private double degrees;
    
    private boolean brake = true;
    
    private boolean wait = true;

    public OnForDegreesCommand(final String[] args) {
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
                degrees = Double.parseDouble(arg);
                break;
              
            case 4:
                brake = Boolean.parseBoolean(arg);
                break;
                
            case 5:
                wait = Boolean.parseBoolean(arg);
            }
            
        }

        tank.onForDegrees(leftSpeed, rightSpeed, degrees, brake, wait);
        
    }
    
    @Override
    public String toString() {
        return String.format("%s %s %s %f %b %b", args[0], leftSpeed, rightSpeed, degrees, brake, wait);
    }

    @Override
    public String help() {
        return "onForDegrees:help";
    }

    public static final OnForDegreesCommand parse(final String[] args) {
        return new OnForDegreesCommand(args);
    }
}
