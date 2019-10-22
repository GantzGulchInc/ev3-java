package com.gantzgulch.lego.platform;

import com.gantzgulch.lego.device.InputDevice;
import com.gantzgulch.lego.device.OutputDevice;
import com.gantzgulch.lego.platform.impl.AbstractPlatform;
import com.gantzgulch.lego.port.InputPort;
import com.gantzgulch.lego.port.OutputPort;

public interface Platform {

    PlatformType getType();
    
	<D extends OutputDevice<?>> D findDevice(Class<D> deviceClass, OutputPort port);

	<D extends InputDevice<?>> D findDevice(Class<D> deviceClass, InputPort port);
	
	public static Platform getInstance() {
		
		return AbstractPlatform.getInstance();
		
	}
}
