package com.gantzgulch.lego.device;

import java.nio.file.Path;
import java.nio.file.Paths;

public enum LedPairId {

	LED_0("/sys/class/leds/led0:green:brick-status", "/sys/class/leds/led0:red:brick-status"),
	LED_1("/sys/class/leds/led1:green:brick-status", "/sys/class/leds/led1:red:brick-status");
	
	private Path color0SysPath;
	private Path color1SysPath;
	
	private LedPairId(final String color0SysPath, final String color1SysPath) {
		
		this.color0SysPath = Paths.get(color0SysPath);
		this.color1SysPath = Paths.get(color1SysPath);
		
	}
	
	public String getType() {
		return "LED";
	}
	
	public Path getColor0SysPath() {
		return color0SysPath;
	}
	
	public Path getColor1SysPath() {
		return color1SysPath;
	}
	
}
