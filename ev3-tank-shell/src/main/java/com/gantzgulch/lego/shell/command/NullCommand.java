package com.gantzgulch.lego.shell.command;

import com.gantzgulch.lego.shell.EV3Shell;
import com.gantzgulch.lego.tank.Tank;

public class NullCommand extends AbstractCommand {

    public NullCommand(final String[] args) {
        super(args);
    }

    @Override
    public void execute(final EV3Shell shell, final Tank tank) {

    }

    @Override
    public String help() {
        return "Null:help";
    }

    public static final NullCommand parse(final String[] args) {
        return new NullCommand(args);
    }
}
