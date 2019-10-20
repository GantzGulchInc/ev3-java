package com.gantzgulch.ev3.device.impl;

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
