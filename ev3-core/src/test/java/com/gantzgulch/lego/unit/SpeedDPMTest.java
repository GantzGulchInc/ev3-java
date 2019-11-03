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
public class SpeedDPMTest {

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

        Arrays.asList(new SpeedDPMData[] { //
                new SpeedDPMData(0.0, 0), //
                new SpeedDPMData(360, 6), //
                new SpeedDPMData(720, 12), //
                new SpeedDPMData(-720, -12), //
                new SpeedDPMData(-360, -6) }) //
                .stream() //
                .forEach(d -> {

                    final SpeedDPM s = new SpeedDPM(d.dpm);

                    assertThat(s.toNative(motor), equalTo(d.nativeValue));

                });

    }

    @Test
    public void testOver() {

        SpeedDPM s = new SpeedDPM(63060);

        thrown.expect(IllegalArgumentException.class);

        s.toNative(motor);

    }

    @Test
    public void testUnder() {

        SpeedDPM s = new SpeedDPM(-63060);

        thrown.expect(IllegalArgumentException.class);

        System.out.println("native: " + s.toNative(motor));

    }

    private static class SpeedDPMData {

        public final double dpm;
        public final int nativeValue;

        public SpeedDPMData(final double dpm, final int nativeValue) {
            this.dpm = dpm;
            this.nativeValue = nativeValue;
        }
    }
}
