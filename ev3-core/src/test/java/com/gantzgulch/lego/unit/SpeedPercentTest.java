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
package com.gantzgulch.lego.unit;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.gantzgulch.lego.device.ev3.EV3Motor.EV3MotorCommand;
import com.gantzgulch.lego.device.ev3.EV3TachoMotor;

@RunWith(MockitoJUnitRunner.class)
public class SpeedPercentTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private EV3TachoMotor<EV3MotorCommand> motor;

    @Before
    public void before() {
        Mockito.when(motor.getMaxSpeed()).thenReturn(1050);
    }

    @Test
    public void testSpeedPercent() {

        Arrays.asList(new SpeedPercentData[] { //
                new SpeedPercentData(0.0, 0), //
                new SpeedPercentData(50.0, 525), //
                new SpeedPercentData(40.5, 425), //
                new SpeedPercentData(100.0, 1050), //
                new SpeedPercentData(-100.0, -1050), //
                new SpeedPercentData(-40.5, -425), //
                new SpeedPercentData(-50.0, -525) }) //
                .stream() //
                .forEach(d -> {

                    final SpeedPercent s = new SpeedPercent(d.percent);

                    assertThat(s.toNative(motor), equalTo(d.nativeValue));

                });

    }

    @Test
    public void testOver() {

        thrown.expect(IllegalArgumentException.class);

        new SpeedPercent(101.0);

    }

    @Test
    public void testUnder() {

        thrown.expect(IllegalArgumentException.class);

        new SpeedPercent(-101.0);

    }

    private static class SpeedPercentData {

        public final double percent;
        public final int nativeValue;

        public SpeedPercentData(final double percent, final int nativeValue) {
            this.percent = percent;
            this.nativeValue = nativeValue;
        }
    }
}
