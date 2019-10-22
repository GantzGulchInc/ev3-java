package com.gantzgulch.lego.motor.ev3;

import java.util.concurrent.TimeUnit;

import com.gantzgulch.lego.device.ev3.EV3Motor.EV3MotorCommand;
import com.gantzgulch.lego.device.ev3.EV3Motor.EV3MotorState;
import com.gantzgulch.lego.device.ev3.EV3Motor.EV3MotorStopAction;
import com.gantzgulch.lego.device.ev3.EV3TachoMotor;
import com.gantzgulch.lego.motor.MotorWrapper;
import com.gantzgulch.lego.units.LengthUnit;
import com.gantzgulch.lego.util.Sleep;
import com.gantzgulch.lego.wheel.Wheel;

public class EV3TachoMotorWrapper implements MotorWrapper {

    private final EV3TachoMotor<EV3MotorCommand> tachoMotor;

    public EV3TachoMotorWrapper(final EV3TachoMotor<EV3MotorCommand> tachoMotor) {
        this.tachoMotor = tachoMotor;
        
    }

    @Override
    public void onForRotations(double rotations, boolean useBrake, final boolean wait) {
        
        final double relativePosition = rotations * tachoMotor.getCountPerRotation();
        
        tachoMotor.setStopAction( useBrake ? EV3MotorStopAction.BRAKE: EV3MotorStopAction.COAST);
        
        tachoMotor.setPosition( (int) relativePosition );
        
        tachoMotor.setCommand(EV3MotorCommand.RUN_TO_REL_POS);
        
        while( wait && tachoMotor.getState().contains(EV3MotorState.RUNNING) ) {
            Sleep.sleep(10, TimeUnit.MILLISECONDS);
        }
    }

    @Override
    public void onForDegrees(double degrees, boolean useBrake, final boolean wait) {
        
        final double rotations = degrees / 360.0;
        
        onForRotations(rotations, true, wait);
        
    }

    @Override
    public void onForDistance(Wheel wheel, double lengthUnit, LengthUnit unit, boolean useBrake, final boolean wait) {
        
        final double lengthMM = unit.toMilliMeters(lengthUnit);
        
        final double rotations = lengthMM / wheel.getCircumferenceMMs();
        
        onForRotations(rotations, true, wait);
        
    }

    @Override
    public void onForever() {
        
    }

    @Override
    public void stop() {
        tachoMotor.setCommand(EV3MotorCommand.STOP);
    }
}
