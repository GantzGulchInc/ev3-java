package com.gantzgulch.ev3.platform.impl;

import com.gantzgulch.ev3.logging.EV3Logger;
import com.gantzgulch.ev3.platform.EV3Platform;

public class EV3PlatformImpl implements EV3Platform {

	private static final EV3Logger LOG = EV3Logger.getLogger(EV3PlatformImpl.class);
	
	private static class EV3PlatformImplSingleton {
		private static final EV3PlatformImpl INSTANCE = new EV3PlatformImpl();
	}
	
	public static EV3PlatformImpl getInstance() {
		return EV3PlatformImplSingleton.INSTANCE;
	}
	
	private EV3PlatformImpl() {
		
		LOG.entering("ctor");
		
	}
	
	@Override
	public String toString() {
		return "EV3PlatformImpl";
	}
}
