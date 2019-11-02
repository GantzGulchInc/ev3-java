package com.gantzgulch.lego.unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gantzgulch.lego.util.lang.Pair;

public class SpeedParser {

    private static final String REGEX_DOUBLE = "(\\-?\\d+(?:\\.\\d+)?)";
    
    private static final Pattern PATTERN_PERCENT = Pattern.compile(REGEX_DOUBLE + "%");
    
    private static final Pattern PATTERN_RPM = Pattern.compile(REGEX_DOUBLE + "rpm");

    private static final Pattern PATTERN_RPS = Pattern.compile(REGEX_DOUBLE + "rps");

    private static final Pattern PATTERN_DPM = Pattern.compile(REGEX_DOUBLE + "dpm");

    private static final Pattern PATTERN_DPS = Pattern.compile(REGEX_DOUBLE + "dps");

    private static final Pattern PATTERN_NATIVE = Pattern.compile(REGEX_DOUBLE + "n");

    private static final List<Pair<Pattern,Function<Double,Speed>>> SPEED_PARSERS = createSpeedParser();
    
    public static Optional<Speed> parse(final String value) {

        if (value == null) {
            return Optional.empty();
        }
        
        for( final Pair<Pattern,Function<Double,Speed>> p : SPEED_PARSERS) {
            
            Optional<Speed> s = accept(p, value);
            
            if( s.isPresent() ) {
                return s;
            }
        }
        
        return Optional.empty();
    }
    
    private static List<Pair<Pattern, Function<Double, Speed>>> createSpeedParser() {
        
        final List<Pair<Pattern, Function<Double, Speed>>> l = new ArrayList<>();
        
        l.add( new Pair<>(PATTERN_PERCENT, d -> { return new SpeedPercent(d); } ) );
        l.add( new Pair<>(PATTERN_RPM, d -> { return new SpeedRPM(d); } ) );
        l.add( new Pair<>(PATTERN_RPS, d -> { return new SpeedRPS(d); } ) );
        l.add( new Pair<>(PATTERN_DPM, d -> { return new SpeedDPM(d); } ) );
        l.add( new Pair<>(PATTERN_DPS, d -> { return new SpeedDPS(d); } ) );
        l.add( new Pair<>(PATTERN_NATIVE, d -> { return new SpeedNative(d); } ) );
        
        return l;
    }

    private static Optional<Speed> accept(final Pair<Pattern,Function<Double,Speed>> pair, final String value) {
        
        final Matcher matcher = pair.getLeft().matcher(value);

        if (matcher.matches()) {

            final Double d = Double.parseDouble(matcher.group(1));
            
            return Optional.of( pair.getRight().apply(d) );
        }

        return Optional.empty();
    }
}
