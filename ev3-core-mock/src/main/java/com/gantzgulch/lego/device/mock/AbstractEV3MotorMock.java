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
package com.gantzgulch.lego.device.mock;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.gantzgulch.lego.api.device.ev3.EV3MotorCommand;
import com.gantzgulch.lego.api.device.ev3.EV3MotorPolarity;
import com.gantzgulch.lego.api.device.ev3.EV3MotorState;
import com.gantzgulch.lego.api.device.ev3.EV3MotorStopAction;
import com.gantzgulch.lego.api.device.ev3.EV3TachoMotor;
import com.gantzgulch.lego.api.unit.Speed;
import com.gantzgulch.lego.common.lang.Sleep;
import com.gantzgulch.lego.common.logger.EV3Logger;

public class AbstractEV3MotorMock implements EV3TachoMotor<EV3MotorCommand>, MockTicker.Process {

    protected final EV3Logger LOG = EV3Logger.getLogger(getClass());

    private final String address;

    private final String driverName;

    private final int countPerRotation;

    private int dutyCycle;

    private int dutyCycleSetPoint;

    private int position = 0;

    private int holdPidKd;

    private int holdPidKi;

    private int holdPidKp;

    private final int maxSpeed;

    private int positionSetPoint;

    private int speed;

    private int speedSetPoint;

    private Duration rampUpSetPoint = Duration.ofMillis(0);

    private Duration rampDownSetPoint = Duration.ofMillis(0);

    private int speedPidKd;

    private int speedPidKi;

    private int speedPidKp;

    private EV3MotorPolarity polarity = EV3MotorPolarity.NORMAL;

    private Set<EV3MotorState> state = Collections.synchronizedSet(new HashSet<>());

    private EV3MotorStopAction stopAction;

    private Duration timeSetPoint = Duration.ofMillis(0);

    
    private EV3MotorCommand currentCommand = null;

    private boolean isClosed = false;

    public AbstractEV3MotorMock(final int countPerRotation, final int maxSpeed, final String address, final String driverName) {
        this.countPerRotation = countPerRotation;
        this.maxSpeed = maxSpeed;
        this.address = address;
        this.driverName = driverName;
    }

    @Override
    public int getCountPerRotation() {
        return countPerRotation;
    }

    @Override
    public int getDutyCycle() {
        return dutyCycle;
    }

    @Override
    public int getDutyCycleSetPoint() {
        return dutyCycleSetPoint;
    }

    @Override
    public void setDutyCycleSetPoint(int newDutyCycleSetPoint) {
        this.dutyCycleSetPoint = newDutyCycleSetPoint;
    }

    @Override
    public EV3MotorPolarity getPolarity() {
        return polarity;
    }

    @Override
    public void setPolarity(EV3MotorPolarity newPolarity) {
        this.polarity = newPolarity;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public void setPosition(int newPosition) {
        this.position = newPosition;
    }

    @Override
    public int getHoldPidKd() {
        return holdPidKd;
    }

    @Override
    public void setHoldPidKd(int newHoldPidKd) {
        this.holdPidKd = newHoldPidKd;
    }

    @Override
    public int getHoldPidKi() {
        return holdPidKi;
    }

    @Override
    public void setHoldPidKi(int newHoldPidKi) {
        this.holdPidKi = newHoldPidKi;
    }

    @Override
    public int getHoldPidKp() {
        return holdPidKp;
    }

    @Override
    public void setHoldPidKp(int newHoldPidKp) {
        this.holdPidKp = newHoldPidKp;
    }

    @Override
    public int getMaxSpeed() {
        return maxSpeed;
    }

    @Override
    public int getPositionSetPoint() {
        return positionSetPoint;
    }

    @Override
    public void setPositionSetPoint(int newPositionSetPoint) {
        this.positionSetPoint = newPositionSetPoint;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public int getSpeedSetPoint() {
        return speedSetPoint;
    }

    @Override
    public void setSpeedSetPoint(final int speed) {
        this.speedSetPoint = speed;
    }

    @Override
    public void setSpeedSetPoint(Speed speed) {

        setSpeedSetPoint( speed.toNative(this) );
        
    }

    @Override
    public Duration getRampUpSetPoint() {
        return rampUpSetPoint;
    }

    @Override
    public void setRampUpSetPoint(final Duration duration) {
        rampUpSetPoint = duration;
    }

    @Override
    public Duration getRampDownSetPoint() {
        return rampDownSetPoint;
    }

    @Override
    public void setRampDownSetPoint(final Duration duration) {
        this.rampUpSetPoint = duration;
    }

    @Override
    public int getSpeedPidKd() {
        return speedPidKd;
    }

    @Override
    public void setSpeedPidKd(int newSpeedPidKd) {
        speedPidKd = newSpeedPidKd;
    }

    @Override
    public int getSpeedPidKi() {
        return speedPidKi;
    }

    @Override
    public void setSpeedPidKi(int newSpeedPidKi) {
        speedPidKi = newSpeedPidKi;
    }

    @Override
    public int getSpeedPidKp() {
        return speedPidKp;
    }

    @Override
    public void setSpeedPidKp(int newSpeedPidKp) {
        speedPidKp = newSpeedPidKp;
    }

    @Override
    public Set<EV3MotorState> getState() {
        return state;
    }

    @Override
    public EV3MotorStopAction getStopAction() {
        return stopAction;
    }

    @Override
    public void setStopAction(EV3MotorStopAction newStopAction) {
        stopAction = newStopAction;
    }

    @Override
    public Set<EV3MotorStopAction> getStopActions() {

        return Arrays//
                .stream(EV3MotorStopAction.values())//
                .collect(Collectors.toSet());
    }

    @Override
    public Duration getTimeSetPoint() {
        return timeSetPoint;
    }

    @Override
    public void setTimeSetPoint(final Duration duration) {
        timeSetPoint = duration;
    }

    @Override
    public boolean isRunning() {
        return state.contains(EV3MotorState.RUNNING);
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String getDriverName() {
        return driverName;
    }

    @Override
    public Set<EV3MotorCommand> getCommands() {

        return Arrays //
                .stream(EV3MotorCommand.values()) //
                .collect(Collectors.toSet());
    }

    @Override
    public void sendCommand(final EV3MotorCommand command) {

        currentCommand = command;

    }

    @Override
    public void close() throws IOException {

        isClosed = true;

    }

    
    @Override
    public boolean tick() {

        if( isClosed ) {
            return true;
        }
        
        if( currentCommand == null ) {
            state.clear();
            return false;
        }
        
        switch( currentCommand ) {
        
        case RESET:
            state.clear();
            currentCommand = null;
            break;
            
        case RUN_DIRECT:
            break;
            
        case RUN_FOREVER:
            break;
            
        case RUN_TIMED:
            break;
            
        case RUN_TO_ABS_POS:
            break;
            
        case RUN_TO_REL_POS:
            break;
            
        case STOP:
            state.clear();
            currentCommand = null;
            break;
            
        }
        
        return false;
    }
    
    
//    private class MotorThread extends Thread {
//
//        private final AbstractEV3MotorMock motor;
//
//        public MotorThread(final AbstractEV3MotorMock motor) {
//            
//            setDaemon(true);
//            
//            this.motor = motor;
//        }
//        
//        @Override
//        public void run() {
//
//            while (!isClosed) {
//
//                try {
//
//                    final EV3MotorCommand command = pendingCommands.pollFirst(50, TimeUnit.MILLISECONDS);
//                    
//                    LOG.finest("run: command: %s", command);
//                    
//                    if( command != null ) {
//                        
//                        switch(command) {
//                        
//                        case RESET:
//                            reset();
//                            break;
//                        case RUN_DIRECT:
//                            runDirect();
//                            break;
//                        case RUN_FOREVER:
//                            runForever();
//                            break;
//                        case RUN_TIMED:
//                            runTimed();
//                            break;
//                        case RUN_TO_ABS_POS:
//                            runToAbsPos();
//                            break;
//                        case RUN_TO_REL_POS:
//                            runToRelPos();
//                            break;
//                        case STOP:
//                            stopAll();
//                            break;
//                        }
//                        
//                    }
//                } catch (final InterruptedException e) {
//                    LOG.finer(e, "run: Interrupted.");
//                }
//            }
//        }
//
//        private void reset() {
//            
//        }
//        
//        private void runDirect() {
//            
//        }
//        
//        private void runForever() {
//            
//        }
//        
//        private void runTimed() {
//            
//        }
//        
//        private void runToAbsPos() {
//            
//        }
//
//        private void runToRelPos() {
//            
//            LOG.finest("runToRelPos: entering");
//            
//            motor.state.clear();
//            
//            motor.state.add(EV3MotorState.RUNNING);
//            
//            int targetPosition = position + positionSetPoint;
//            
//            int sign = position > targetPosition ? -1 : 1;
//            
//            LOG.finest("runToRelPos: sign: %d", sign);
//            
//            LOG.finest("runToRelPos: speedSetPoint: %d", speedSetPoint);
//            
//            int incPerLoop = Math.abs(speedSetPoint) / 100;
//            
//            LOG.finest("runToRelPos: position: %d", position);
//            LOG.finest("runToRelPos: positionSetPoint: %d", positionSetPoint);
//            
//            LOG.finest("runToRelPos: incPerLoop: %s", incPerLoop);
//            
//            while(true) {
//                
//                int diff = Math.abs( position - targetPosition );
//                
//                if( diff < incPerLoop ) {
//                    
//                    position = targetPosition;
//                    
//                    break;
//                }
//
//                position += sign * incPerLoop;
//                
//                // LOG.finest("runToRelPos: position: %d", position);
//                
//                Sleep.sleep(10, TimeUnit.MILLISECONDS);
//            }
//            
//            motor.state.clear();
//        }
//        
//        private void stopAll() {
//            
//        }
//    }
}
