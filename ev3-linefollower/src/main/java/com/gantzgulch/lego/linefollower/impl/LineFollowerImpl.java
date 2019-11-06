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
package com.gantzgulch.lego.linefollower.impl;

import com.gantzgulch.lego.api.device.ev3.EV3ColorSensor;
import com.gantzgulch.lego.api.device.ev3.EV3MotorCommand;
import com.gantzgulch.lego.api.device.ev3.EV3TachoMotor;
import com.gantzgulch.lego.api.unit.Length;
import com.gantzgulch.lego.api.wheel.Wheel;
import com.gantzgulch.lego.common.logger.EV3Logger;
import com.gantzgulch.lego.linefollower.LineFollower;
import com.gantzgulch.lego.linefollower.LineFollowerConfiguration;

public class LineFollowerImpl implements LineFollower {

    private static final EV3Logger LOG = EV3Logger.getLogger(LineFollowerImpl.class);

    private final EV3TachoMotor<EV3MotorCommand> leftMotor;

    private final EV3TachoMotor<EV3MotorCommand> rightMotor;

    private final Wheel wheel;

    private final EV3ColorSensor colorSensor;

    private double kp;

    private double ki;

    private double kd;

    private int targetSpeedNative;
    private int leftSpeedNative;
    private int rightSpeedNative;

    private int leftMotorLastPosition;
    private int leftMotorTotalTravel;

    private int rightMotorLastPosition;
    private int rightMotorTotalTravel;

    private int targetTotalTravel; 
    
    private double pidIntegral = 0.0;
    private double pidLastError = 0.0;
    private double pidDerivative = 0.0;

    public LineFollowerImpl(//
            final EV3TachoMotor<EV3MotorCommand> leftMotor, //
            final EV3TachoMotor<EV3MotorCommand> rightMotor, //
            final Wheel wheel, //
            final EV3ColorSensor colorSensor) {
        
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;
        this.wheel = wheel;
        this.colorSensor = colorSensor;
    }

    @Override
    public void followLine(final LineFollowerConfiguration config, final Length length) {

        initState(config, length);
        
        while(true) {
            
            if( updatePosition() ) {
                
                leftMotor.sendCommand(EV3MotorCommand.STOP);
                rightMotor.sendCommand(EV3MotorCommand.STOP);
                
                break;
            }
            
            
            
            
            
            
            
        }
    }

    private boolean updatePosition() {
        
        int leftMotorCurrentPosition = leftMotor.getPosition();
        
        int rightMotorCurrentPosition = rightMotor.getPosition();
        
        leftMotorTotalTravel += leftMotorCurrentPosition - leftMotorLastPosition;
        
        rightMotorTotalTravel += rightMotorCurrentPosition - rightMotorLastPosition;
        
        leftMotorLastPosition = leftMotorCurrentPosition;
        
        rightMotorLastPosition = rightMotorCurrentPosition;
        
        return ( ( leftMotorTotalTravel + rightMotorTotalTravel ) / 2 ) > targetTotalTravel;
    }
    
    
    private void initState(final LineFollowerConfiguration config, final Length length) {
        
        
        this.kp = config.getKp();
        this.ki = config.getKi();
        this.kd = config.getKd();
        
        this.targetSpeedNative = config.getSpeed().toNative(leftMotor);
        this.leftSpeedNative = targetSpeedNative;
        this.rightSpeedNative = targetSpeedNative;

        this.leftMotorLastPosition = leftMotor.getPosition();
        this.leftMotorTotalTravel = 0;

        this.rightMotorLastPosition = rightMotor.getPosition();
        this.rightMotorTotalTravel = 0;

        this.targetTotalTravel = (int)(length.toMillimeters() / wheel.getCircumferenceMMs() * leftMotor.getCountPerRotation());
        
        this.pidIntegral = 0.0;
        this.pidLastError = 0.0;
        this.pidDerivative = 0.0;

    }

}
