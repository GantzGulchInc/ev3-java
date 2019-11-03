package com.gantzgulch.lego.platform.common;

import java.io.Closeable;
import java.util.Optional;

import com.gantzgulch.lego.util.lang.BidirectionalEnumMap;

public interface Attribute extends Closeable {

    byte[] readBytes();

    String readString();

    void writeString(String value);
    
    String[] readStringArray();

    Optional<Integer> readInteger();

    void writeInteger(int value);

    <E extends Enum<?>> Optional<E> readEnum(BidirectionalEnumMap<E> enumMap);

    <E extends Enum<?>> void writeEnum(E enumValue, BidirectionalEnumMap<E> enumMap);

}
