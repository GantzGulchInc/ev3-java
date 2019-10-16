package com.gantzgulch.ev3.app;

import com.gantzgulch.ev3.logging.EV3Logger;
import com.gantzgulch.ev3.platform.EV3Platform;

public class Main {

	private static final EV3Logger LOG = EV3Logger.getLogger(Main.class);

	public static void main(final String[] args) {
		
		final EV3Platform platform = EV3Platform.getInstance();

		LOG.info("Platform: %s", platform);
		
	}

}
