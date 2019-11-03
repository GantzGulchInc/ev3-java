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
package com.gantzgulch.lego.unit;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class DurationParser {

    private static final String REGEX_LONG = "(\\d+)";

    private static final Pattern PATTERN_MINUTES = Pattern.compile("^" + REGEX_LONG + "m$");
    private static final Pattern PATTERN_SECONDS = Pattern.compile(REGEX_LONG+"s");
    private static final Pattern PATTERN_MILLI_SECONDS = Pattern.compile(REGEX_LONG+"ms");

    private static final List<PatternParser<Long,Duration>> DURATION_PARSERS = createDurationParser();
    
    public static Duration parse(final String value) {

        for( final PatternParser<Long,Duration> p : DURATION_PARSERS) {
            
            Optional<Duration> s = p.accept(value);
            
            if( s.isPresent() ) {
                return s.get();
            }
        }
        
        throw new IllegalArgumentException("Unable to parse duration: " + value);
    }
    
    private static List<PatternParser<Long,Duration>> createDurationParser() {
        
        final List<PatternParser<Long,Duration>> l = new ArrayList<>();
        
        l.add( new PatternParser<Long,Duration>(PATTERN_SECONDS, Long::parseLong, Duration::ofSeconds));
        l.add( new PatternParser<Long,Duration>(PATTERN_MILLI_SECONDS, Long::parseLong, Duration::ofMillis));
        l.add( new PatternParser<Long,Duration>(PATTERN_MINUTES, Long::parseLong, Duration::ofMinutes));
        
        return l;
    }

}
