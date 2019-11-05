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

import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public enum AttributeType {

	READ_ONLY(true, false, "r", StandardOpenOption.READ), //
	READ_ONLY_CACHED(true, false, "r", StandardOpenOption.READ), //
	WRITE_ONLY(false, true, "rw", StandardOpenOption.WRITE), //
	READ_WRITE(true, true, "rw", StandardOpenOption.READ, StandardOpenOption.WRITE);

    private final String mode;
	private final Set<OpenOption> openOptions;
	private final boolean isReadable;
	private final boolean isWritable;

	private AttributeType(final boolean isReadable, final boolean isWritable, final String mode, final OpenOption... openOptions) {

		this.isReadable = isReadable;
		this.isWritable = isWritable;
        this.mode = mode;
		this.openOptions = createOpenOptions(openOptions);

	}
	
	public boolean isReadable() {
		return isReadable;
	}
	
	public boolean isWritable() {
		return isWritable;
	}
	
	public String getMode() {
        return mode;
    }
	
	public Set<OpenOption> getOpenOptions(){
		return openOptions;
	}
	
	private static Set<OpenOption> createOpenOptions(final OpenOption...openOptions) {

		final Set<OpenOption> options = new HashSet<>();
		
		Arrays//
				.stream(openOptions) //
				.forEach(oo -> options.add(oo));
		
		return Collections.unmodifiableSet(options);

	}
}
