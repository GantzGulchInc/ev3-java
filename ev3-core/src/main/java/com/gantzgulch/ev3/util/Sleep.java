package com.gantzgulch.ev3.util;

import com.gantzgulch.ev3.logging.EV3Logger;

public class Sleep {

	private static final EV3Logger LOG = EV3Logger.getLogger(Sleep.class);

	private Sleep() {
	}

	public static void sleep(final long millis) {

		try {
			
			Thread.sleep(millis);
			
		} catch (final InterruptedException e) {
			LOG.warning(e, "Sleep was interrupted.");
		}
	}
}
