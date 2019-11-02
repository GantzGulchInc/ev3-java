package com.gantzgulch.lego.shell.command;

import com.gantzgulch.lego.shell.EV3Shell;
import com.gantzgulch.lego.tank.Tank;

public class HelpCommand extends AbstractCommand {


    public HelpCommand(final String[] args) {
        super(false, args);
    }

    @Override
    public void execute(final EV3Shell shell, final Tank tank) {

    }

    @Override
    public String help() {
        return "Help:help";
    }
    
    public static final HelpCommand parse(final String[] args) {
        return new HelpCommand(args);
    }
}
