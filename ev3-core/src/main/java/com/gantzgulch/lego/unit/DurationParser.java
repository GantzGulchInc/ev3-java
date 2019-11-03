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
