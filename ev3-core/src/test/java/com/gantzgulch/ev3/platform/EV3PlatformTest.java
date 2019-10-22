package com.gantzgulch.ev3.platform;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.gantzgulch.lego.platform.Platform;

public class EV3PlatformTest {

	@Test
	public void testPlatformExistance() {
		
		final Platform platform = Platform.getInstance();
		
		assertThat(platform, Matchers.notNullValue());
	}
}
