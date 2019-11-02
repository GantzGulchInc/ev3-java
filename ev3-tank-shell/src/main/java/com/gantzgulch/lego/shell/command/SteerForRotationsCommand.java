package com.gantzgulch.lego.shell.command;

import com.gantzgulch.lego.shell.EV3Shell;
import com.gantzgulch.lego.tank.Tank;
import com.gantzgulch.lego.unit.Speed;

public class SteerForRotationsCommand extends AbstractCommand {

    private int steering;
    
    private Speed speed;
    
    private double rotations;
    
    private boolean brake = true;
    
    private boolean wait = true;

    public SteerForRotationsCommand(final String[] args) {
        super(args);
    }

    @Override
    public void execute(final EV3Shell shell, final Tank tank) {
        
        if( args.length < 3 ) {
            throw new IllegalArgumentException("Invalid argument count.");
        }

        for(int i=0; i<args.length; i++) {
            
            final String arg = args[i];
            
            LOG.finest("execute: Parsing: [%d]:%s", i, arg);
            
            switch(i) {
            case 1:
                steering = Integer.parseInt(arg);
                break;
                
            case 2: 
                speed = Speed.parse(arg);
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

        tank.steerForRotations(steering, speed, rotations, brake, wait);
        
    }

    @Override
    public String help() {
        return "steerForRotations:help";
    }

    public static final SteerForRotationsCommand parse(final String[] args) {
        return new SteerForRotationsCommand(args);
    }
}
