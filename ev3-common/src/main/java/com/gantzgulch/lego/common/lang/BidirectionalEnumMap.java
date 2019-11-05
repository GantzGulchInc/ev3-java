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
package com.gantzgulch.lego.common.lang;

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
