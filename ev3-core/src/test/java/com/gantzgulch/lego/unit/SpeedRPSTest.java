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
public class SpeedRPSTest {

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

        Arrays.asList(new SpeedRPSData[] { //
                new SpeedRPSData(0.0, 0), //
                new SpeedRPSData(1, 360), //
                new SpeedRPSData(2, 720), //
                new SpeedRPSData(1.5, 540), //
                new SpeedRPSData(-1.5, -540), //
                new SpeedRPSData(-2, -720), //
                new SpeedRPSData(-1, -360) }) //
                .stream() //
                .forEach(d -> {

                    final SpeedRPS s = new SpeedRPS(d.rps);

                    assertThat(s.toNative(motor), equalTo(d.nativeValue));

                });

    }

    
    @Test
    public void testOver() {

        SpeedRPS s = new SpeedRPS(2.92);

        thrown.expect(IllegalArgumentException.class);

        s.toNative(motor);

    }
    
    @Test
    public void testUnder() {

        SpeedRPS s = new SpeedRPS(-2.92);

        thrown.expect(IllegalArgumentException.class);

        s.toNative(motor);

    }
    
    private static class SpeedRPSData {

        public final double rps;
        public final int nativeValue;

        public SpeedRPSData(final double rps, final int nativeValue) {
            this.rps = rps;
            this.nativeValue = nativeValue;
        }
    }
}
