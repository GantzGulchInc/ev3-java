package com.gantzgulch.ev3.platform;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Test;

public class EV3PlatformTest {

	@Test
	public void testPlatformExistance() {
		
		final EV3Platform platform = EV3Platform.getInstance();
		
		assertThat(platform, Matchers.notNullValue());
	}
}
