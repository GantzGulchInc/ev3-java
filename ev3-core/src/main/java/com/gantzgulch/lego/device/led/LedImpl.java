package com.gantzgulch.lego.device.led;

import java.nio.file.Path;

import com.gantzgulch.lego.device.Led;
import com.gantzgulch.lego.platform.impl.Attribute;
import com.gantzgulch.lego.platform.impl.AttributeType;

public class LedImpl implements Led {

	private static final Integer ZERO = Integer.valueOf(0);
	
	private final Path sysPath;

	private final Attribute maxBrightness;
	
	private final Attribute brightness;
	
	public LedImpl(final Path sysPath) {
		
		this.sysPath = sysPath;
		
		this.maxBrightness = new Attribute(AttributeType.READ_ONLY, false, this.sysPath, "max_brightness");
		this.brightness = new Attribute(AttributeType.READ_WRITE, false, this.sysPath, "brightness");
	}

	@Override
	public int getMaxBrightness() {
		return maxBrightness.readInteger().orElse(ZERO);
	}
	
	@Override
	public int getBrightness() {
		return brightness.readInteger().orElse(ZERO);
	}
	
	@Override
	public void setBrightness(final int newBrightness) {
		
		brightness.writeInteger(newBrightness);
		
	}

}
