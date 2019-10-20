package com.gantzgulch.ev3.device.led;

import com.gantzgulch.ev3.device.Led;
import com.gantzgulch.ev3.device.LedPair;
import com.gantzgulch.ev3.device.LedPairId;

public class LedPairImpl implements LedPair {

	private final LedPairId ledPairDevice;
	
	private final Led color0Led;
	
	private final Led color1Led;

	public LedPairImpl(final LedPairId ledPairDevice) {
		
		this.ledPairDevice = ledPairDevice;
		this.color0Led = new LedImpl(this.ledPairDevice.getColor0SysPath());
		this.color1Led = new LedImpl(this.ledPairDevice.getColor1SysPath());
	}

	@Override
	public int getMaxBrightness0() {
		return color0Led.getMaxBrightness();
	}

	@Override
	public int getMaxBrightness1() {
		return color1Led.getMaxBrightness();
	}

	@Override
	public int getBrightness0() {
		return color0Led.getBrightness();
	}

	@Override
	public int getBrightness1() {
		return color1Led.getBrightness();
	}

	@Override
	public void setBrightness(final int brightness0, final int brightness1) {
		
		color0Led.setBrightness(brightness0);
		color1Led.setBrightness(brightness1);

	}

}
