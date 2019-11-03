package com.gantzgulch.lego.unit;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gantzgulch.lego.util.lang.Pair;

public class DurationParser {

    private static final String REGEX_LONG = "(\\d+)";

    private static final Pattern PATTERN_MINUTES = Pattern.compile(REGEX_LONG+"m");
    private static final Pattern PATTERN_SECONDS = Pattern.compile(REGEX_LONG+"s");
    private static final Pattern PATTERN_MILLI_SECONDS = Pattern.compile(REGEX_LONG+"ms");

    private static final List<Pair<Pattern,Function<Long,Duration>>> SPEED_PARSERS = createDurationParser();
    
    public static Duration parse(final String value) {

        if (value == null) {
            throw new IllegalArgumentException("Unable to parse duration: " + value);
        }
        
        for( final Pair<Pattern,Function<Long,Duration>> p : SPEED_PARSERS) {
            
            Optional<Duration> s = accept(p, value);
            
            if( s.isPresent() ) {
                return s.get();
            }
        }
        
        throw new IllegalArgumentException("Unable to parse duration: " + value);
    }
    
    private static Optional<Duration> accept(final Pair<Pattern,Function<Long,Duration>> pair, final String value) {
        
        final Matcher matcher = pair.getLeft().matcher(value);
        
        if (matcher.matches()) {
            
            final Long d = Long.parseLong(matcher.group(1));
            
            return Optional.of( pair.getRight().apply(d) );
        }
        
        return Optional.empty();
    }

    private static List<Pair<Pattern,Function<Long,Duration>>> createDurationParser() {
        
        final List<Pair<Pattern,Function<Long,Duration>>> l = new ArrayList<>();
        
        l.add( new Pair<>(PATTERN_SECONDS, d -> { return Duration.ofSeconds(d); } ) );
        l.add( new Pair<>(PATTERN_MILLI_SECONDS, d -> { return Duration.ofMillis(d); } ) );
        l.add( new Pair<>(PATTERN_MINUTES, d -> { return Duration.ofMinutes(d); } ) );
        
        return l;
    }

}
