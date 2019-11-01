package com.gantzgulch.lego.unit;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SpeedPercentTest {

    public static final double delta = 0.000000001;
    
    public SpeedPercentTest() {
    }

    
    @Test
    public void parseValidPercent() {
        
        Speed s = null;
        
        s = Speed.parse("45.2%");
        
        assertThat(s, isA(SpeedPercent.class));
        
        SpeedPercent sp = (SpeedPercent)(s); 
        
        assertThat(sp.getPercent(), closeTo(45.2, delta));
        
    }

    @Test
    public void parseValidPercent2() {
        
        Speed s = null;
        
        s = Speed.parse("45%");
        
        assertThat(s, isA(SpeedPercent.class));
        
        SpeedPercent sp = (SpeedPercent)(s); 
        
        assertThat(sp.getPercent(), closeTo(45, delta));
        
    }
        


    @Test(expected = IllegalArgumentException.class)
    public void parseInvalidPercent() {
        
        Speed.parse("4A5.2%");
        
    }

}
