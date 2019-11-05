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

import java.util.List;
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
    
    public static <P1,P2> Optional<P2> parse(final String value, final List<PatternParser<P1,P2>> parsers) {

        for( final PatternParser<P1,P2> p : parsers) {
            
            Optional<P2> s = p.accept(value);
            
            if( s.isPresent() ) {
                return s;
            }
        }
        
        return Optional.empty();
    }
}
