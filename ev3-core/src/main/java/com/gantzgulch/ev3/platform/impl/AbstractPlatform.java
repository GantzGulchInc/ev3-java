package com.gantzgulch.ev3.platform.impl;

import java.util.HashMap;
import java.util.Map;

import com.gantzgulch.ev3.device.InputDevice;
import com.gantzgulch.ev3.device.LedPair;
import com.gantzgulch.ev3.device.LedPairId;
import com.gantzgulch.ev3.device.OutputDevice;
import com.gantzgulch.ev3.logging.EV3Logger;
import com.gantzgulch.ev3.platform.EV3Platform;
import com.gantzgulch.ev3.port.InputPort;
import com.gantzgulch.ev3.port.OutputPort;
import com.gantzgulch.ev3.util.Pair;

public abstract class AbstractPlatform implements EV3Platform {

    protected final EV3Logger LOG = EV3Logger.getLogger(getClass());

    private static class PlatformSingleton {
        private static final AbstractPlatform INSTANCE = new com.gantzgulch.ev3.platform.ev3.EV3Platform();
    }

    public static AbstractPlatform getInstance() {
        return PlatformSingleton.INSTANCE;
    }

    private Map<Pair<Class<? extends InputDevice<?>>, InputPort>, InputDevice<?>> inputCache = new HashMap<>();

    private Map<Pair<Class<? extends OutputDevice<?>>, OutputPort>, OutputDevice<?>> outputCache = new HashMap<>();

    protected AbstractPlatform() {

        LOG.entering("ctor");

    }

    protected <D extends InputDevice<?>> D getCachedDevice(final Class<D> deviceClass, final InputPort port) {
        return deviceClass.cast(inputCache.get(new Pair<>(deviceClass, port)));
    }

    protected void addCachedDevice(final Class<? extends InputDevice<?>> deviceClass, final InputPort port, final InputDevice<?> device) {
        inputCache.put(new Pair<>(deviceClass, port), device);
    }

    protected <D extends OutputDevice<?>> D getCachedDevice(final Class<D> deviceClass, final OutputPort port) {
        return deviceClass.cast(outputCache.get(new Pair<>(deviceClass, port)));
    }

    protected void addCachedDevice(final Class<? extends OutputDevice<?>> deviceClass, final OutputPort port, final OutputDevice<?> device) {
        outputCache.put(new Pair<>(deviceClass, port), device);
    }

    @Override
    public LedPair getLedPair(LedPairId deviceId) {
        // TODO Auto-generated method stub
        return null;
    }

//	@Override
//	public LedPair getLedPair(final LedPairId deviceId) {
//
//		final String deviceKey = createDeviceKey(deviceId, deviceId.name());
//		
//		Optional<LedPair> lpo = getCachedDevice(deviceKey, LedPair.class); 
//		
//		if( lpo.isEmpty() ) {
//			
//			lpo = Optional.of( new LedPairImpl(deviceId) );
//			
//			addCachedDevice(deviceKey, lpo.get() );
//			
//		}
//		
//		return lpo.get();
//	}

    @Override
    public String toString() {
        return "EV3PlatformImpl";
    }

}
