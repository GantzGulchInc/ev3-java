package com.gantzgulch.lego.device.ev3;

import com.gantzgulch.lego.device.OutputDevice;

public interface EV3Motor<C extends Enum<?>> extends OutputDevice<C> {

    public enum EV3MotorCommand {
        RUN_FOREVER, //
        RUN_TO_ABS_POS, //
        RUN_TO_REL_POS, //
        RUN_TIMED, //
        RUN_DIRECT, //
        STOP, //
        RESET;
    }

    public enum EV3MotorPolarity {
        NORMAL, //
        INVERSED;
    }

    public enum EV3MotorState {
        RUNNING, //
        RAMPING, //
        HOLDING, //
        OVERLOADED, //
        STALLED;
    }

    public enum EV3MotorStopAction {
        COAST, //
        BRAKE, //
        HOLD; //
    }

}
