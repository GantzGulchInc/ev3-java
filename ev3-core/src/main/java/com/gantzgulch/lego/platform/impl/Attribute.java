package com.gantzgulch.lego.platform.impl;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Optional;

import com.gantzgulch.lego.exception.AttributeException;
import com.gantzgulch.lego.util.BidirectionalEnumMap;
import com.gantzgulch.lego.util.StringUtil;

public class Attribute {

    public static final Charset UTF_8 = StandardCharsets.UTF_8;

    private final AttributeType type;

    private final Path sysPath;

    private FileChannel fileChannel;

    private final ByteBuffer buffer;

    public Attribute(final AttributeType type, final Path sysPath) {
        this.type = type;
        this.sysPath = sysPath;
        this.buffer = ByteBuffer.allocate(200);
    }

    public Attribute(final AttributeType type, final Path sysPath, final String... attributeName) {
        this(type, resolve(sysPath, attributeName));
    }

    public String readString() {

        if (!type.isReadable()) {
            throw new AttributeException("Attribute is not readable: " + sysPath);
        }

        synchronized (buffer) {

            try {

                final FileChannel fc = getOpenFileChannel();

                fc.position(0);

                buffer.clear();

                while (fc.read(buffer) >= 0) {
                    // Keep reading.
                }

                buffer.flip();

                while (buffer.hasRemaining()) {

                    int pos = buffer.limit() - 1;

                    byte b = buffer.get(pos);

                    if (b == '\n' || b == '\r') {
                        buffer.limit(pos);
                    } else {
                        break;
                    }
                }

                return UTF_8.decode(buffer).toString();

            } catch (final IOException e) {
                throw new AttributeException("Unable to read attribute: " + sysPath, e);
            }

        }

    }

    public void writeString(final String value) {

        if (!type.isWritable()) {
            throw new AttributeException("Attribute is not writable: " + sysPath);
        }

        synchronized (buffer) {

            try {

                buffer.clear();

                buffer.put(value.getBytes(UTF_8));

                buffer.put((byte) '\n');

                buffer.flip();

                final FileChannel fc = getOpenFileChannel();

                fc.position(0);

                fc.write(buffer);

            } catch (final IOException e) {
                throw new AttributeException("Error writing value.", e);
            }
        }
    }

    public String[] readStringArray() {
        return readString().split(" ");
    }

    public Optional<Integer> readInteger() {
        return StringUtil.parseInteger(readString());
    }

    public void writeInteger(final int value) {
        writeString(Integer.toString(value));
    }

    public <E extends Enum<?>> Optional<E> readEnum(final BidirectionalEnumMap<E> enumMap) {
        return enumMap.get(readString());
    }
    
    public <E extends Enum<?>> void writeEnum(final E enumValue, final BidirectionalEnumMap<E> enumMap) {

        enumMap.get(enumValue).ifPresent(s -> {
            writeString(s);
        });

    }

    private FileChannel getOpenFileChannel() throws IOException {

        if (fileChannel == null) {

            fileChannel = FileChannel.open(sysPath, type.getOpenOptions());

        }

        return fileChannel;
    }

    private static Path resolve(final Path path, String... attributeName) {

        Path result = path;

        for (final String an : attributeName) {
            result = result.resolve(an);
        }

        return result;
    }

}
