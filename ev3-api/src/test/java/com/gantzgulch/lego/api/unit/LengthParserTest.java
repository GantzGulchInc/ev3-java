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
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class LengthParserTest {

    private static final double DELTA = 0.00000001;
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Test
    public void parseMillimeters() {

        assertLength(LengthParser.parse("10mm"), 10);
        
    }
    
    @Test
    public void parseCentimeters() {

        assertLength(LengthParser.parse("1cm"), 10);
        
        assertLength(LengthParser.parse("5.5cm"), 55);

        assertLength(LengthParser.parse("-2.3cm"), -23);
        
    }
    
    @Test
    public void parseMeters() {

        assertLength(LengthParser.parse("1m"), 1000);
        
        assertLength(LengthParser.parse("1.05m"), 1050);

        assertLength(LengthParser.parse("923.2m"), 923200);

    }
    
    @Test
    public void parseInches() {

        assertLength(LengthParser.parse("1in"), 25.4);
        
    }
    
    @Test
    public void parseInvalidLength() {
        
        thrown.expect(IllegalArgumentException.class);
        
        DurationParser.parse("9a3m");
        
    }

    private void assertLength(final Length length, final double millis) {
        
        assertThat(length, notNullValue());
        
        assertThat(length.toMillimeters(), closeTo(millis, DELTA));
        
    }

}
