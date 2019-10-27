package com.gantzgulch.lego.platform.ev3;

import com.gantzgulch.lego.device.ev3.EV3Led.LedColor;
import com.gantzgulch.lego.util.lang.BidirectionalEnumMap;

public class EV3LedColorMap extends BidirectionalEnumMap<LedColor> {

    public static final EV3LedColorMap INSTANCE = new EV3LedColorMap();

    public EV3LedColorMap() {

        super(LedColor.values());

        add(LedColor.GREEN, "green");
        add(LedColor.RED, "red");
    }

}
