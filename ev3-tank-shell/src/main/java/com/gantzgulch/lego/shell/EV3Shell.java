package com.gantzgulch.lego.shell;

import java.util.Scanner;

import com.gantzgulch.lego.tank.Tank;

public class EV3Shell implements Runnable {

    private final Tank tank;

    private final CommandParser parser;

    private boolean isRunning = true;
    
    public EV3Shell(final Tank tank) {
        this.tank = tank;
        this.parser = new CommandParser();
    }

    public void run() {

        try (final Scanner scanner = new Scanner(System.in)) {

            while (isRunning) {

                System.out.print(">");
                System.out.flush();

                final String line = scanner.nextLine();

                Command command = null;
                
                try {

                    command = parser.parse(line);

                    command.execute(this, tank);

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
}
