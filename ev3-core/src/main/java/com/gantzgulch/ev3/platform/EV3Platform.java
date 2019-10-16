package com.gantzgulch.ev3.platform;

import com.gantzgulch.ev3.platform.impl.EV3PlatformImpl;

public interface EV3Platform {

	
	public static EV3Platform getInstance() {
		
		return EV3PlatformImpl.getInstance();
		
	}
}
