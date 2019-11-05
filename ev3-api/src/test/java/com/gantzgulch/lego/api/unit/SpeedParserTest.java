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
package com.gantzgulch.lego.api.unit;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.gantzgulch.lego.api.device.ev3.EV3MotorCommand;
import com.gantzgulch.lego.api.device.ev3.EV3TachoMotor;
import com.gantzgulch.lego.api.unit.Speed;
import com.gantzgulch.lego.api.unit.SpeedParser;
import com.gantzgulch.lego.api.unit.SpeedPercent;
import com.gantzgulch.lego.api.unit.SpeedRPM;

@RunWith(MockitoJUnitRunner.class)
public class SpeedParserTest {

    public static final double delta = 0.000000001;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private EV3TachoMotor<EV3MotorCommand> motor;

    @Test
    public void parsePercentages() {

        assertPercentage(SpeedParser.parse("0%"), 0);

        assertPercentage(SpeedParser.parse("1%"), 1);

        assertPercentage(SpeedParser.parse("-1%"), -1);

        assertPercentage(SpeedParser.parse("25.2%"), 25.2);

        assertPercentage(SpeedParser.parse("-35.5%"), -35.5);

        assertPercentage(SpeedParser.parse("100.0%"), 100);
    }

    @Test
    public void parseInvalidPercent() {

        thrown.expect(IllegalArgumentException.class);

        SpeedParser.parse("4A5.2%");

    }

    @Test
    public void parseOverPercent() {

        thrown.expect(IllegalArgumentException.class);

        SpeedParser.parse("100.1%");

    }

    @Test
    public void parseUnderPercent() {

        thrown.expect(IllegalArgumentException.class);

        SpeedParser.parse("-100.1%");

    }

    @Test
    public void parseRPM() {

        assertRPM(SpeedParser.parse("0rpm"), 0);

        assertRPM(SpeedParser.parse("1rpm"), 1);

        assertRPM(SpeedParser.parse("-1rpm"), -1);

        assertRPM(SpeedParser.parse("25.2rpm"), 25.2);

        assertRPM(SpeedParser.parse("-35.5rpm"), -35.5);

        assertRPM(SpeedParser.parse("100.0rpm"), 100);
    }

    @Test
    public void parseInvalidRPM() {

        thrown.expect(IllegalArgumentException.class);

        SpeedParser.parse("4,23rpm");

    }

    private void assertPercentage(final Speed speed, final double percentage) {

        assertThat(speed, isA(SpeedPercent.class));

        SpeedPercent sp = (SpeedPercent) speed;

        assertThat(sp.getPercent(), closeTo(percentage, delta));

    }

    private void assertRPM(final Speed speed, final double rpm) {

        assertThat(speed, isA(SpeedRPM.class));

        SpeedRPM sp = (SpeedRPM) speed;

        assertThat(sp.getRpm(), closeTo(rpm, delta));

    }

}
