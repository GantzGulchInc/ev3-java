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
package com.gantzgulch.lego.ev3dev.attribute;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Optional;

import com.gantzgulch.lego.api.exception.AttributeException;
import com.gantzgulch.lego.common.lang.BidirectionalEnumMap;
import com.gantzgulch.lego.common.lang.StringUtil;

public class AttributeFileChannel implements Attribute {

    public static final Charset UTF_8 = StandardCharsets.UTF_8;

    private final AttributeType type;

    private final Path sysPath;

    private FileChannel fileChannel;

    private final ByteBuffer buffer;
    
    private String value;

    protected AttributeFileChannel(final AttributeType type, boolean preWarm, final Path sysPath) {
        this.type = type;
        this.sysPath = sysPath;
        this.buffer = ByteBuffer.allocate(200);

        if (preWarm) {

            try {
                getOpenFileChannel();
            } catch (final IOException | RuntimeException e) {
                throw new AttributeException("Unable to create attribute.", e);
            }

        }
    }

    public AttributeFileChannel(final AttributeType type, boolean preWarm, final Path sysPath, final String... attributeName) {
        this(type, preWarm, resolve(sysPath, attributeName));
    }

    @Override
    public void close() throws IOException {

        if (fileChannel != null) {
            fileChannel.close();
        }

    }

    @Override
    public byte[] readBytes() {

        synchronized (buffer) {

            try {

                final FileChannel fc = getOpenFileChannel();

                fc.position(0);

                buffer.clear();

                fc.read(buffer);

                buffer.flip();

                final byte[] bytes = new byte[buffer.remaining()];

                buffer.get(bytes);

                return bytes;

            } catch (final IOException e) {
                throw new AttributeException("Unable to read attribute: " + sysPath, e);
            }

        }
    }

    @Override
    public String readString() {

        if (!type.isReadable()) {
            throw new AttributeException("Attribute is not readable: " + sysPath);
        }

        if( type == AttributeType.READ_ONLY_CACHED && value != null ) {
            return value;
        }
        
        synchronized (buffer) {

            try {

                final FileChannel fc = getOpenFileChannel();

                fc.position(0);

                buffer.clear();

                fc.read(buffer);

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

                value = UTF_8.decode(buffer).toString();
                
                return value;

            } catch (final IOException e) {
                throw new AttributeException("Unable to read attribute: " + sysPath, e);
            }

        }

    }

    @Override
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

    @Override
    public String[] readStringArray() {
        return readString().split(" ");
    }

    @Override
    public Optional<Integer> readInteger() {
        return StringUtil.parseInteger(readString());
    }

    @Override
    public void writeInteger(final int value) {
        writeString(Integer.toString(value));
    }

    @Override
    public <E extends Enum<?>> Optional<E> readEnum(final BidirectionalEnumMap<E> enumMap) {
        return enumMap.get(readString());
    }

    @Override
    public <E extends Enum<?>> void writeEnum(final E enumValue, final BidirectionalEnumMap<E> enumMap) {

        Optional<String> v = enumMap.get(enumValue);

        if (v.isPresent()) {
            writeString(v.get());
        }

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
