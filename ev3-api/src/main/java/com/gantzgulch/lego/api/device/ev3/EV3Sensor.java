/*******************************************************************************
 *    Copyright 2019 GantzGulch, Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *******************************************************************************/
package com.gantzgulch.lego.api.device.ev3;

import java.time.Duration;
import java.util.Set;

import com.gantzgulch.lego.api.device.InputDevice;

public interface EV3Sensor<CMDS extends Enum<?>, MODES extends Enum<?>> extends InputDevice<CMDS> {

    byte[] getBinData();
    
    EV3SensorBinFormat getBinDataFormat();
    
    String getFwVersion();
    
    MODES getMode();
    
    void setMode(MODES mode);
    
    Set<MODES> getModes();
    
    int getNumValues();
    
    int getPollMillis();
    
    void setPollMillis(Duration duration);
    
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

}
