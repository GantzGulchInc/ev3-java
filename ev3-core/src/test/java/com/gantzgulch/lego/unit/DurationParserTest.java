package com.gantzgulch.lego.unit;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.time.Duration;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DurationParserTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Test
    public void parseMinutes() {

        assertDuration(DurationParser.parse("1m"), 60000);
        
    }
    
    @Test
    public void parseSeconds() {

        assertDuration(DurationParser.parse("1s"), 1000);
        
        assertDuration(DurationParser.parse("2s"), 2000);

        assertDuration(DurationParser.parse("370s"), 370000);
        
    }
    
    @Test
    public void parseMillis() {

        assertDuration(DurationParser.parse("320ms"), 320);
        
        assertDuration(DurationParser.parse("7050ms"), 7050);

        assertDuration(DurationParser.parse("0ms"), 0);

    }
    
    @Test
    public void parseInvalidDuration() {
        
        thrown.expect(IllegalArgumentException.class);
        
        DurationParser.parse("-1m");
        
    }

    private void assertDuration(final Duration duration, final long millis) {
        
        assertThat(duration, notNullValue());
        
        assertThat(duration.toMillis(), equalTo(millis));
        
    }

}
