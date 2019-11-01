package com.gantzgulch.lego.shell;

import com.gantzgulch.lego.tank.Tank;

public interface Command {

    void execute(EV3Shell shell, Tank tank);
 
    String help();
}
