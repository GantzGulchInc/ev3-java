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

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gantzgulch.lego.device.ev3.EV3Motor.EV3MotorCommand;
import com.gantzgulch.lego.unit.SpeedPercent;
import com.gantzgulch.lego.util.lang.Closeables;
import com.gantzgulch.lego.util.lang.Sleep;
import com.gantzgulch.lego.util.logger.EV3Logger;

public class EV3LargeMotorMockTest {

    private static final EV3Logger LOG = EV3Logger.getLogger(EV3LargeMotorMockTest.class);
    
    private EV3LargeMotorMock motor;
    
    @Before
    public void before() {
        motor = new EV3LargeMotorMock();
    }
    
    @After
    public void after() {
        Closeables.close(motor);
    }
    
    public EV3LargeMotorMockTest() {
    }

    @Test
    public void testRunToRelPos() {
        
        motor.setSpeedSetPoint( new SpeedPercent(30) );
        motor.setPositionSetPoint(900);
        
        motor.sendCommand(EV3MotorCommand.RUN_TO_REL_POS);
        
        while( ! motor.isRunning() ) {
            // Wait for motor to start running.
        }
        
        while(motor.isRunning()) {
            
            LOG.info("Position: %d", motor.getPosition());
         
            Sleep.sleep(10, TimeUnit.MILLISECONDS);
        }
        
    }
}
