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

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.time.Duration;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.gantzgulch.lego.api.unit.DurationParser;

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
