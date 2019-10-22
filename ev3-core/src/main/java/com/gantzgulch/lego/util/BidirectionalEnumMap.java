package com.gantzgulch.lego.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class BidirectionalEnumMap<T extends Enum<?>> {

    private final Map<T, String> enumToStringMap = new HashMap<>();
    private final Map<String, T> stringToEnumMap = new HashMap<>();

    public BidirectionalEnumMap() {

    }

    public void add(final T e, final String s) {
        enumToStringMap.put(e, s);
        stringToEnumMap.put(s, e);
    }

    public Optional<String> get(final T e) {
        return Optional.ofNullable(enumToStringMap.get(e));
    }

    public Optional<T> get(final String s) {
        return Optional.ofNullable(stringToEnumMap.get(s));
    }

    public Set<T> get(final String... s) {

        if (s == null) {
            return Collections.emptySet();
        }

        return Arrays //
                .stream(s) //
                .map(v -> {
                    return stringToEnumMap.get(v);
                }) //
                .filter(e -> {
                    return e != null;
                }) //
                .collect(Collectors.toSet());
    }

}
