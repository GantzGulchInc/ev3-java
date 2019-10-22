package com.gantzgulch.lego.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class BidirectionalEnumMap<T extends Enum<?>> {

    private final T[] enumArray;
    private final String[] stringArray;
    
    public BidirectionalEnumMap(final T[] values) {
        enumArray = Cast.cast(new Enum<?>[values.length]);
        stringArray = new String[values.length];
    }

    public void add(final T e, final String s) {
        enumArray[ e.ordinal() ] = e;
        stringArray[ e.ordinal() ] = s;
    }

    public Optional<String> get(final T e) {
        
        if( e == null ) {
            return Optional.empty();
        }
        
        return Optional.ofNullable( stringArray[ e.ordinal() ]);
    }

    public Optional<T> get(final String s) {
        
        if( s == null ) {
            return Optional.empty();
        }
        
        for(int i=0; i<stringArray.length; i++) {
            if( s.contentEquals(stringArray[i]) ) {
                return Optional.ofNullable( enumArray[i] );
            }
        }
        
        return Optional.empty();
    }

    public Set<T> get(final String... s) {

        if (s == null) {
            return Collections.emptySet();
        }

        return Arrays //
                .stream(s) //
                .map(v -> {
                    return get(v).orElse(null);
                }) //
                .filter(e -> {
                    return e != null;
                }) //
                .collect(Collectors.toSet());
    }

}
