package com.gantzgulch.lego.unit;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.gantzgulch.lego.device.ev3.EV3Motor.EV3MotorCommand;
import com.gantzgulch.lego.device.ev3.EV3TachoMotor;

@RunWith(MockitoJUnitRunner.class)
public class SpeedParserTest {

    public static final double delta = 0.000000001;
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Mock
    private EV3TachoMotor<EV3MotorCommand> motor;
    
    @Test
    public void parsePercentages() {
        
        assertPercentage(Speed.parse("0%"), 0);

        assertPercentage(Speed.parse("1%"), 1);

        assertPercentage(Speed.parse("-1%"), -1);

        assertPercentage(Speed.parse("25.2%"), 25.2);

        assertPercentage(Speed.parse("-35.5%"), -35.5);

        assertPercentage(Speed.parse("100.0%"), 100);
    }
    
    @Test
    public void parseInvalidPercent() {
        
        thrown.expect(IllegalArgumentException.class);
        
        Speed.parse("4A5.2%");
        
    }

    @Test
    public void parseOverPercent() {
        
        thrown.expect(IllegalArgumentException.class);
        
        Speed.parse("100.1%");
        
    }

    @Test
    public void parseUnderPercent() {
        
        thrown.expect(IllegalArgumentException.class);
        
        Speed.parse("-100.1%");
        
    }
    
    

    @Test
    public void parseRPM() {
        
        assertRPM(Speed.parse("0rpm"), 0);

        assertRPM(Speed.parse("1rpm"), 1);

        assertRPM(Speed.parse("-1rpm"), -1);

        assertRPM(Speed.parse("25.2rpm"), 25.2);

        assertRPM(Speed.parse("-35.5rpm"), -35.5);

        assertRPM(Speed.parse("100.0rpm"), 100);
    }
    
    @Test
    public void parseInvalidRPM() {
        
        thrown.expect(IllegalArgumentException.class);
        
        Speed.parse("4,23rpm");
        
    }


    
    private void assertPercentage(final Speed speed, final double percentage) {
        
        assertThat(speed, isA(SpeedPercent.class) );
        
        SpeedPercent sp = (SpeedPercent)speed;
        
        assertThat(sp.getPercent(), closeTo(percentage, delta));
        
    }

    private void assertRPM(final Speed speed, final double rpm) {
        
        assertThat(speed, isA(SpeedRPM.class) );
        
        SpeedRPM sp = (SpeedRPM)speed;
        
        assertThat(sp.getRpm(), closeTo(rpm, delta));
        
    }

}
