package com.gantzgulch.lego.unit;

import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternParser<T1,R> {

    private final Pattern pattern;
    
    private final Function<String,T1> type;
    
    private final Function<T1,R> func;
    
    public PatternParser(final Pattern pattern, final Function<String,T1> type, final Function<T1,R> func) {
        this.pattern = pattern;
        this.type = type;
        this.func = func;
    }
    
    public Optional<R> accept(final String value) {
        
        if( value == null ) {
            return Optional.empty();
        }
        
        final Matcher matcher = pattern.matcher(value);
        
        if (matcher.matches()) {
            
            final T1 d = type.apply(matcher.group(1));
            
            final R r = func.apply(d);
            
            return Optional.of( r );
        }
        
        return Optional.empty();
    }
    
}
