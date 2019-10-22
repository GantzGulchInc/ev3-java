package com.gantzgulch.lego.device.ev3;

import com.gantzgulch.lego.device.OutputDevice;

public interface EV3Motor<CMDS extends Enum<?>> extends OutputDevice<CMDS> {

    public static enum EV3MotorCommand {
        RUN_FOREVER, //
        RUN_TO_ABS_POS, //
        RUN_TO_REL_POS, //
        RUN_TIMED, //
        RUN_DIRECT, //
        STOP, //
        RESET;
    }

    public static enum EV3MotorPolarity {
        NORMAL, //
        INVERSED;
    }

    public static enum EV3MotorState {
        RUNNING, //
        RAMPING, //
        HOLDING, //
        OVERLOADED, //
        STALLED;
    }

    public static enum EV3MotorStopAction {
        COAST, //
        BRAKE, //
        HOLD; //
    }

}
