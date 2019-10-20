package com.gantzgulch.ev3.platform;

import com.gantzgulch.ev3.device.InputDevice;
import com.gantzgulch.ev3.device.LedPair;
import com.gantzgulch.ev3.device.LedPairId;
import com.gantzgulch.ev3.device.OutputDevice;
import com.gantzgulch.ev3.platform.impl.AbstractPlatform;
import com.gantzgulch.ev3.port.InputPort;
import com.gantzgulch.ev3.port.OutputPort;

public interface EV3Platform {

	LedPair getLedPair(LedPairId deviceId);
	
	<D extends OutputDevice<?>> D findDevice(Class<D> deviceClass, OutputPort port);

	<D extends InputDevice<?>> D findDevice(Class<D> deviceClass, InputPort port);
	
	public static EV3Platform getInstance() {
		
		return AbstractPlatform.getInstance();
		
	}
}
