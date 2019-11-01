package com.gantzgulch.lego.shell.command;

import com.gantzgulch.lego.shell.EV3Shell;
import com.gantzgulch.lego.tank.Tank;

public class QuitCommand extends AbstractCommand {


    public QuitCommand(final String[] args) {
        super(args);
    }

    @Override
    public void execute(final EV3Shell shell, final Tank tank) {
        shell.stop();
    }

    @Override
    public String help() {
        return "quit:help";
    }

    public static final QuitCommand parse(final String[] args) {
        return new QuitCommand(args);
    }
}
