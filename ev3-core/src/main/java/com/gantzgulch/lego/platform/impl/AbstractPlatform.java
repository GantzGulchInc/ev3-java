package com.gantzgulch.lego.platform.impl;

import java.util.HashMap;
import java.util.Map;

import com.gantzgulch.lego.device.InputDevice;
import com.gantzgulch.lego.device.LedPair;
import com.gantzgulch.lego.device.LedPairId;
import com.gantzgulch.lego.device.OutputDevice;
import com.gantzgulch.lego.logging.EV3Logger;
import com.gantzgulch.lego.platform.Platform;
import com.gantzgulch.lego.platform.PlatformType;
import com.gantzgulch.lego.port.InputPort;
import com.gantzgulch.lego.port.OutputPort;
import com.gantzgulch.lego.util.Pair;

public abstract class AbstractPlatform implements Platform {

    protected final EV3Logger LOG = EV3Logger.getLogger(getClass());

    private static class PlatformSingleton {
        private static final AbstractPlatform INSTANCE = new com.gantzgulch.lego.platform.ev3.EV3Platform();
    }

    public static AbstractPlatform getInstance() {
        return PlatformSingleton.INSTANCE;
    }
    
    private final PlatformType type;

    private final Map<Pair<Class<? extends InputDevice<?>>, InputPort>, InputDevice<?>> inputCache = new HashMap<>();

    private final Map<Pair<Class<? extends OutputDevice<?>>, OutputPort>, OutputDevice<?>> outputCache = new HashMap<>();


    protected AbstractPlatform(final PlatformType type) {

        LOG.entering("ctor");

        this.type = type;
    }

    @Override
    public PlatformType getType() {
        return type;
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
