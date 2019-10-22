package com.gantzgulch.lego.util;

import java.util.concurrent.TimeUnit;

import com.gantzgulch.lego.logging.EV3Logger;

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

	public static void sleep(final long timeUnit, final TimeUnit unit) {
	    sleep(unit.toMillis(timeUnit));
	}
}
