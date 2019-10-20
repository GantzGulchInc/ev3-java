package com.gantzgulch.ev3.device.impl;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Optional;

import com.gantzgulch.ev3.util.FileOperator;

public class Attribute {

    public static final Charset UTF_8 = StandardCharsets.UTF_8;

    private final AttributeType type;

    private final Path sysPath;

    private FileChannel fileChannel;

    private ByteBuffer buffer;
    
    public Attribute(final AttributeType type, final Path sysPath) {
        this.type = type;
        this.sysPath = sysPath;
        this.buffer = ByteBuffer.allocate(200);
    }

    public Attribute(final AttributeType type, final Path sysPath, final String ... attributeName) {
        this(type, resolve(sysPath, attributeName) );
    }

    public void writeString(final String value) {
        writeTrimmedString(value);
    }
    
    public String readString() {
        return readTrimmedString();
    }

    public String[] readStringArray() {
        return readTrimmedString().split(" ");
    }

    public Optional<Integer> readInteger() {

        try {

            return Optional.of( Integer.parseInt(readTrimmedString()));

        } catch (final NumberFormatException e) {
            return Optional.empty();
        }
    }

    public void writeInteger(final int value) {

        writeTrimmedString(Integer.toString(value));
        
    }

    private void writeTrimmedString(final String value) {

        if( ! type.isWritable() ) {
            throw new AttributeException("Attribute is not writable: " + sysPath);
        }
        
        try {

            buffer.clear();
            
            buffer.put(value.getBytes(UTF_8));
            
            buffer.put( (byte) '\n' );

            buffer.flip();
            
            final FileChannel fc = getOpenFileChannel();
            
            fc.position(0);
            
            fc.write(buffer);

        } catch (final IOException e) {
            throw new AttributeException("Error writing value.", e);
        }
    }

    private String readTrimmedString() {

        if (!type.isReadable()) {
            throw new AttributeException("Attribute is not readable: " + sysPath);
        }

        try {
            
            final FileChannel fc = getOpenFileChannel();
            
            fc.position(0);
            
            return FileOperator.readString(fc, buffer).stripTrailing();

        } catch (final IOException e) {
            throw new AttributeException("Unable to read attribute: " + sysPath, e);
        }
    }

    private FileChannel getOpenFileChannel() throws IOException {

        if (fileChannel == null) {

            fileChannel = FileChannel.open(sysPath, type.getOpenOptions());

        }

        return fileChannel;
    }

    private static Path resolve(final Path path, String ... attributeName) {
        
        Path result = path;
        
        for(final String an : attributeName) {
            result = result.resolve(an);
        }
        
        return result;
    }


}
