package com.gantzgulch.lego.unit;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SpeedRPMTest {

    public static final double delta = 0.000000001;
    
    public SpeedRPMTest() {
    }

    
    @Test
    public void parseValidRPM() {
        
        Speed s = null;
        
        s = Speed.parse("45.2rpm");
        
        SpeedRPM srpm = (SpeedRPM)s;
        
        assertThat(srpm.getRpm(), closeTo(45.2, delta));
        
        
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseInvalidRPM() {
        
        Speed.parse("4A5.2rpm");
        
    }

}
