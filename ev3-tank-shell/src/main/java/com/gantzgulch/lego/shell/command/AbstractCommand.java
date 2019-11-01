package com.gantzgulch.lego.shell.command;

import com.gantzgulch.lego.shell.Command;
import com.gantzgulch.lego.util.logger.EV3Logger;

public abstract class AbstractCommand implements Command {

    protected EV3Logger LOG = EV3Logger.getLogger(getClass());

    protected String[] args;

    public AbstractCommand(final String[] args) {
        this.args = args;
    }

}
