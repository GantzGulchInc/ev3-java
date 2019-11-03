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

        Command command = null;
        
        try (final Scanner scanner = new Scanner(System.in)) {

            while (isRunning) {

                System.out.print("ev3>");
                System.out.flush();

                final String line = scanner.nextLine();

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
            
        }
    };
    
    public void stop() {
        isRunning = false;
    }
    
    public Recorder getRecorder() {
        return recorder;
    }
}
