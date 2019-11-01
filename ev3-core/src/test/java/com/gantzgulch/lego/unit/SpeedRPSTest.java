package com.gantzgulch.lego.unit;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SpeedRPSTest {

    public static final double delta = 0.000000001;

    public SpeedRPSTest() {
    }

    @Test
    public void parseValidRPS() {

        Speed s = null;

        s = Speed.parse("45.2rps");

        SpeedRPS srps = (SpeedRPS) s;

        assertThat(srps.getRps(), closeTo(45.2, delta));

    }

    @Test(expected = IllegalArgumentException.class)
    public void parseInvalidRPS() {

        Speed.parse("4A5.2rps");

    }

}
