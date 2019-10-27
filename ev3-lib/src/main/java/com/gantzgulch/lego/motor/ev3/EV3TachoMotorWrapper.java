package com.gantzgulch.lego.motor.ev3;

import java.util.concurrent.TimeUnit;

import com.gantzgulch.lego.device.ev3.EV3Motor.EV3MotorCommand;
import com.gantzgulch.lego.device.ev3.EV3Motor.EV3MotorState;
import com.gantzgulch.lego.device.ev3.EV3Motor.EV3MotorStopAction;
import com.gantzgulch.lego.device.ev3.EV3TachoMotor;
import com.gantzgulch.lego.motor.MotorWrapper;
import com.gantzgulch.lego.unit.Speed;
import com.gantzgulch.lego.units.LengthUnit;
import com.gantzgulch.lego.util.lang.Sleep;
import com.gantzgulch.lego.util.logger.EV3Logger;
import com.gantzgulch.lego.wheel.Wheel;

public class EV3TachoMotorWrapper implements MotorWrapper {

    private static final EV3Logger LOG = EV3Logger.getLogger(EV3TachoMotorWrapper.class);
    
    private final EV3TachoMotor<EV3MotorCommand> tachoMotor;

    public EV3TachoMotorWrapper(final EV3TachoMotor<EV3MotorCommand> tachoMotor) {
        this.tachoMotor = tachoMotor;
    }

    @Override
    public void setSpeed(Speed speed) {
        tachoMotor.setSpeedSetPoint( computeSpeed(speed, tachoMotor));
    }
    
    @Override
    public void setBrake(boolean useBrake) {
        tachoMotor.setStopAction( useBrake ? EV3MotorStopAction.BRAKE: EV3MotorStopAction.COAST);
    }
    
    @Override
    public void onForTime(final long timeUnit, final TimeUnit unit, boolean wait) {
        
        tachoMotor.setTimeSetPoint(timeUnit, unit);
        
        tachoMotor.sendCommand(EV3MotorCommand.RUN_TIMED);
        
        while( wait && tachoMotor.getState().contains(EV3MotorState.RUNNING) ) {
            Sleep.sleep(10, TimeUnit.MILLISECONDS);
        }
    }
    
    @Override
    public void onForRotations(double rotations, final boolean wait) {
        
        final int relativePosition = (int)(rotations * tachoMotor.getCountPerRotation());
        
        tachoMotor.setPositionSetPoint( relativePosition );
        
        tachoMotor.sendCommand(EV3MotorCommand.RUN_TO_REL_POS);
        
        while( wait && tachoMotor.getState().contains(EV3MotorState.RUNNING) ) {
            Sleep.sleep(10, TimeUnit.MILLISECONDS);
        }
    }

    @Override
    public void onForDegrees(double degrees, final boolean wait) {
        
        final double rotations = degrees / 360.0;
        
        onForRotations(rotations, wait);
        
    }

    @Override
    public void onForDistance(Wheel wheel, double lengthUnit, LengthUnit unit, final boolean wait) {
        
        final double lengthMM = unit.toMilliMeters(lengthUnit);
        
        final double rotations = lengthMM / wheel.getCircumferenceMMs();
        
        onForRotations(rotations, wait);
        
    }

    @Override
    public void onForever() {

        tachoMotor.sendCommand(EV3MotorCommand.RUN_FOREVER);
        
    }

    @Override
    public void stop() {
        tachoMotor.sendCommand(EV3MotorCommand.STOP);
    }
    
    private int computeSpeed(final Speed speed, final EV3TachoMotor<EV3MotorCommand> motor) {
        
        int countsPerRotation = motor.getCountPerRotation();
        
        // LOG.finest("computeSpeed: countsPerRotation: %d", countsPerRotation);
        
        int maxCountsPerSecond = motor.getMaxSpeed();
        
        // LOG.finest("computeSpeed: maxCountsPerSecond: %d", maxCountsPerSecond);
        
        double maxRotationsPerSecond = (double) maxCountsPerSecond / (double)countsPerRotation;
        
        // LOG.finest("computeSpeed: maxRotationsPerSecond: %f", maxRotationsPerSecond);
        
        double rotationsPerSecond = speed.rotationsPerSecond(maxRotationsPerSecond);

        // LOG.finest("computeSpeed: rotationsPerSecond: %f", rotationsPerSecond);
        
        int countsPerSecond = (int)(rotationsPerSecond * countsPerRotation);
        
        // LOG.finest("computeSpeed: countsPerSecond: %d", countsPerSecond);
        
        return countsPerSecond;
    }
}
