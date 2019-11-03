package com.gantzgulch.lego.unit;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.gantzgulch.lego.device.ev3.EV3Motor.EV3MotorCommand;
import com.gantzgulch.lego.device.ev3.EV3TachoMotor;

@RunWith(MockitoJUnitRunner.class)
public class SpeedDPSTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private EV3TachoMotor<EV3MotorCommand> motor;

    @Before
    public void before() {
        when(motor.getCountPerRotation()).thenReturn(360);
        when(motor.getMaxSpeed()).thenReturn(1050);
    }

    @Test
    public void testSpeedPercent() {

        Arrays.asList(new SpeedDPSData[] { //
                new SpeedDPSData(0.0, 0), //
                new SpeedDPSData(360, 360), //
                new SpeedDPSData(720, 720), //
                new SpeedDPSData(1.5, 1), //
                new SpeedDPSData(-1.5, -1), //
                new SpeedDPSData(-720, -720), //
                new SpeedDPSData(-360, -360) }) //
                .stream() //
                .forEach(d -> {

                    final SpeedDPS s = new SpeedDPS(d.dps);

                    assertThat(s.toNative(motor), equalTo(d.nativeValue));

                });

    }

    @Test
    public void testOver() {

        SpeedDPS s = new SpeedDPS(1051);

        thrown.expect(IllegalArgumentException.class);

        s.toNative(motor);

    }

    @Test
    public void testUnder() {

        SpeedDPS s = new SpeedDPS(-1051);

        thrown.expect(IllegalArgumentException.class);

        s.toNative(motor);

    }

    private static class SpeedDPSData {

        public final double dps;
        public final int nativeValue;

        public SpeedDPSData(final double dps, final int nativeValue) {
            this.dps = dps;
            this.nativeValue = nativeValue;
        }
    }
}
