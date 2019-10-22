package com.gantzgulch.lego.device.ev3;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.gantzgulch.lego.device.InputDevice;

public interface EV3Sensor<CMDS extends Enum<?>, MODES extends Enum<?>> extends InputDevice<CMDS> {

    byte[] getBinData();
    
    EV3SensorBinFormat getBinDataFormat();
    
    String getFwVersion();
    
    MODES getMode();
    
    void setMode(MODES mode);
    
    Set<MODES> getModes();
    
    int getNumValues();
    
    int getPollMillis();
    
    void setPollMillis(long timeUnit, TimeUnit unit);
    
    String getUnits();
    
    int getValue0();
    
    double getValue0_double();
    
    int getValue1();
    
    double getValue1_double();
    
    int getValue2();
    
    double getValue2_double();

    int getValue3();

    double getValue3_double();
    
    int getValue4();
    
    double getValue4_double();
    
    int getValue5();
    
    double getValue5_double();
    
    int getValue6();
    
    double getValue6_double();
    
    int getValue7();
    
    double getValue7_double();
    
    public enum EV3SensorBinFormat {

        U8, //
        S8, //
        U16, //
        S16, //
        S16_BE, //
        S32, //
        S32_BE, //
        FLOAT ;

    }
    
    
    public enum EV3SensorCommand {
        
    }

    public enum EV3SensorMode {
        
    }

}
