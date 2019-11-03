/*******************************************************************************
 *    Copyright 2019 GantzGulch, Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *******************************************************************************/
package com.gantzgulch.lego.tank.impl;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import com.gantzgulch.lego.device.ev3.EV3Motor.EV3MotorCommand;
import com.gantzgulch.lego.device.ev3.EV3Motor.EV3MotorStopAction;
import com.gantzgulch.lego.device.ev3.EV3TachoMotor;
import com.gantzgulch.lego.tank.Tank;
import com.gantzgulch.lego.unit.Speed;
import com.gantzgulch.lego.unit.SpeedNative;
import com.gantzgulch.lego.units.LengthUnit;
import com.gantzgulch.lego.util.lang.Pair;
import com.gantzgulch.lego.util.lang.Sleep;
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

        int leftSpeedNative = leftSpeed.toNative(leftMotor);
        int rightSpeedNative = rightSpeed.toNative(rightMotor);
        
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

        leftDegrees *= Math.signum(leftSpeedNative);
        
        setSpeedAndStopAction(leftMotor, leftSpeedNative, brake);
        leftMotor.setPositionSetPoint( computePosition(leftDegrees, leftMotor));

        rightDegrees *= Math.signum(rightSpeedNative);
        
        setSpeedAndStopAction(rightMotor, rightSpeedNative, brake);
        rightMotor.setPositionSetPoint( computePosition(rightDegrees, rightMotor));
        
        sendCommand(EV3MotorCommand.RUN_TO_REL_POS);
        
        waitForIt(wait);
    }
    
    @Override
    public void onForRotations(final Speed leftSpeed, final Speed rightSpeed, final double rotations, final boolean brake, final boolean wait) {
        
        LOG.finer("onForRotations: leftSpeed: %s, rightSpeed: %s, rotations: %f, brake: %s, wait: %s", leftSpeed, rightSpeed, rotations, brake, wait);
        
        onForDegrees(leftSpeed, rightSpeed, rotations * 360.0, brake, wait);
    }

    @Override
    public void onForDuration(final Speed leftSpeed, final Speed rightSpeed, final Duration duration, final boolean brake, final boolean wait) {
        
        int leftSpeedNative = leftSpeed.toNative(leftMotor);
        int rightSpeedNative = rightSpeed.toNative(rightMotor);

        setSpeedAndStopAction(leftMotor, leftSpeedNative, brake);
        setSpeedAndStopAction(rightMotor, rightSpeedNative, brake);
        
        leftMotor.setTimeSetPoint(duration);
        rightMotor.setTimeSetPoint(duration);
        
        sendCommand(EV3MotorCommand.RUN_TIMED);
        
        waitForIt(wait);
    }
    
    @Override
    public void steerForRotations(final int steering, final Speed speed, final double rotations, final boolean brake, final boolean wait) {
        
        final Pair<Integer,Integer> speeds = computeSteering(steering, speed);
        
        onForRotations(new SpeedNative(speeds.getLeft()), new SpeedNative(speeds.getRight()), rotations, brake, wait);
    }
    
    private Pair<Integer,Integer> computeSteering(final int steering, final Speed speed) {

        int speedNative = speed.toNative(leftMotor);
        
        int leftSpeed = speedNative;
        int rightSpeed = speedNative;
        
        double speedFactor = (50.0 - Math.abs( (double) steering)) / 50.0;
        
        if( steering >= 0 ) {
            rightSpeed *= speedFactor;
        } else {
            leftSpeed *= speedFactor;
        }

        return new Pair<>(leftSpeed,rightSpeed);
    }
    
    private void waitForIt(final boolean wait) {
        
        while( wait && ( leftMotor.isRunning() || rightMotor.isRunning() ) ) {
            Sleep.sleep(50, TimeUnit.MILLISECONDS);
        }
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
