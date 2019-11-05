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

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public final class FileOperator {

	public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

	private FileOperator() {
	}

	public static String readStringStripTrailing(final Path path, final int bufferSize) throws IOException {
	
		String s = readString(path, bufferSize);
		
		return s.stripTrailing();
	}
	
    public static String readStringStripTrailing(final FileChannel fc, final int bufferSize) throws IOException {
        
        final ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
        
        String s = readString(fc, buffer);
        
        return s.stripTrailing();
    }
    
	public static String readString(final Path path, final int bufferSize) throws IOException {

		try (final FileChannel fc = FileChannel.open(path, StandardOpenOption.READ)) {

			final ByteBuffer buffer = ByteBuffer.allocate(bufferSize);

			return readString(fc, buffer);
		}

	}

	public static String readString(final FileChannel fc, final ByteBuffer buffer) throws IOException {
		
	    buffer.clear();
	    
		read(fc, buffer);
		
		buffer.flip();
		
		return StandardCharsets.UTF_8.decode(buffer).toString();
		
	}
	
	public static void read(final FileChannel fc, final ByteBuffer buffer) throws IOException {

		while ( fc.read(buffer) >= 0) {
		    // Keep reading.
		}

	}

}
