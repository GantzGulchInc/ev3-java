package com.gantzgulch.lego.tank.impl;

import java.time.Duration;
import java.util.NavigableMap;

import com.gantzgulch.lego.device.ev3.EV3Motor.EV3MotorCommand;
import com.gantzgulch.lego.device.ev3.EV3Motor.EV3MotorStopAction;
import com.gantzgulch.lego.device.ev3.EV3TachoMotor;
import com.gantzgulch.lego.tank.Tank;
import com.gantzgulch.lego.unit.Speed;
import com.gantzgulch.lego.units.LengthUnit;
import com.gantzgulch.lego.util.lang.Pair;
import com.gantzgulch.lego.util.logger.EV3Logger;
import com.gantzgulch.lego.wheel.Wheel;

public class EV3TachoTank implements Tank {

    private static final EV3Logger LOG = EV3Logger.getLogger(EV3TachoTank.class);
    
    private final EV3TachoMotor<EV3MotorCommand> leftMotor;
    private final EV3TachoMotor<EV3MotorCommand> rightMotor;
    private final Wheel wheel;

    public EV3TachoTank(EV3TachoMotor<EV3MotorCommand> leftMotor, EV3TachoMotor<EV3MotorCommand> rightMotor, final Wheel wheel) {
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;
        this.wheel = wheel;
    }

    @Override
    public void setRampUp(final Duration duration) {

        leftMotor.setRampUpSetPoint(duration);
        rightMotor.setRampUpSetPoint(duration);

    }

    @Override
    public void setRampDown(final Duration duration) {

        leftMotor.setRampDownSetPoint(duration);
        rightMotor.setRampDownSetPoint(duration);

    }

    @Override
    public void onForDegrees(final Speed leftSpeed, final Speed rightSpeed, final double degrees, final boolean brake, final boolean wait) {

        int leftSpeedNative = computeNativeSpeed(leftSpeed, leftMotor);
        int rightSpeedNative = computeNativeSpeed(rightSpeed, rightMotor);
        
        double leftDegrees;
        double rightDegrees;
        
        if( Math.abs(degrees) < 1.0 || ( leftSpeedNative == 0 && rightSpeedNative == 0 ) ) {
            
            leftDegrees = 0.0;
            
            rightDegrees = 0.0;
            
        } else if ( Math.abs(leftSpeedNative) > Math.abs(rightSpeedNative) ) {
            
            leftDegrees = degrees;
            
            rightDegrees = Math.abs( (double) rightSpeedNative / (double) leftSpeedNative ) * degrees;
            
        } else {
            
            leftDegrees = Math.abs( (double) leftSpeedNative / (double)rightSpeedNative ) * degrees;
            
            rightDegrees = degrees;
        }

        setSpeedAndStopAction(leftMotor, leftSpeedNative, brake);
        leftMotor.setPositionSetPoint( computePosition(leftDegrees, leftMotor));

        setSpeedAndStopAction(rightMotor, rightSpeedNative, brake);
        rightMotor.setPositionSetPoint( computePosition(rightDegrees, rightMotor));
        
        sendCommand(EV3MotorCommand.RUN_TO_REL_POS);
        
    }
    
    @Override
    public void onForRotations(final Speed leftSpeed, final Speed rightSpeed, final double rotations, final boolean brake, final boolean wait) {
        
        LOG.finer("onForRotations: leftSpeed: %s, rightSpeed: %s, rotations: %f, brake: %s, wait: %s", leftSpeed, rightSpeed, rotations, brake, wait);
        
        onForDegrees(leftSpeed, rightSpeed, rotations * 360.0, brake, wait);
    }

    private void sendCommand(final EV3MotorCommand command) {
        leftMotor.sendCommand(command);
        rightMotor.sendCommand(command);
    }
    
    private void setSpeedAndStopAction(final EV3TachoMotor<EV3MotorCommand> motor, final int speedNative, final boolean brake) {
        
        LOG.finer("setSpeedAndStopAction: speedNative: %d, brake: %s", speedNative, brake);
        
        motor.setSpeedSetPoint(speedNative);
        motor.setStopAction(brake ? EV3MotorStopAction.BRAKE : EV3MotorStopAction.COAST);
    }
    
    private int computeNativeSpeed(final Speed speed, final EV3TachoMotor<EV3MotorCommand> motor) {
        
        LOG.fine("computeNativeSpeed: speed: %s", speed);
        
        LOG.fine("computeNativeSpeed: maxSpeed: %d", motor.getMaxSpeed());

        double maxRotationsPerSecond = motor.getMaxSpeed() / motor.getCountPerRotation();
        
        double rotationsPerSecond = speed.rotationsPerSecond(maxRotationsPerSecond);

        LOG.fine("computeNativeSpeed: rotationsPerSecond: %f", rotationsPerSecond);
        
        LOG.fine("computeNativeSpeed: countPerRotation: %d", motor.getCountPerRotation());

        int nativeSpeed = (int)(rotationsPerSecond * motor.getCountPerRotation());
        
        LOG.fine("computeNativeSpeed: nativeSpeed: %d", nativeSpeed);
        
        return nativeSpeed;
    }
    
    private int computePosition(final double degrees, final EV3TachoMotor<EV3MotorCommand> motor) {
        
        LOG.finer("computePosition: degrees: %f", degrees);
        
        int position = (int)( degrees / 360.0 * motor.getCountPerRotation() );
        
        LOG.finer("computePosition: position: %d", position);
        
        return position;
        
    }

    private Pair<Integer,Integer> computePosition(final double lengthUnit, final LengthUnit unit) {

        final double length = unit.toMilliMeters(lengthUnit);

        final double rotations = length / wheel.getCircumferenceMMs();

        final double positionLeft = rotations * leftMotor.getCountPerRotation();
        
        final double positionRight = rotations * rightMotor.getCountPerRotation();

        return new Pair<>( (int) positionLeft, (int) positionRight );
    }
}
