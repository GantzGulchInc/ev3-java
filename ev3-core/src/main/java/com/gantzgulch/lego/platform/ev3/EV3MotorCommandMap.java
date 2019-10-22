package com.gantzgulch.lego.platform.ev3;

import com.gantzgulch.lego.device.ev3.EV3Motor.EV3MotorCommand;
import com.gantzgulch.lego.util.BidirectionalEnumMap;

public class EV3MotorCommandMap extends BidirectionalEnumMap<EV3MotorCommand> {

    public static final EV3MotorCommandMap INSTANCE = new EV3MotorCommandMap();
    
    public EV3MotorCommandMap() {
        
        super(EV3MotorCommand.values());
        
        add(EV3MotorCommand.RESET, "reset");
        add(EV3MotorCommand.RUN_DIRECT, "run-direct");
        add(EV3MotorCommand.RUN_FOREVER, "run-forever");
        add(EV3MotorCommand.RUN_TIMED, "run-timed");
        add(EV3MotorCommand.RUN_TO_ABS_POS, "run-to-abs-pos");
        add(EV3MotorCommand.RUN_TO_REL_POS, "run-to-rel-pos");
        add(EV3MotorCommand.STOP, "stop");
    }

}
