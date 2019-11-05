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
package com.gantzgulch.lego.platform.brickpi;

import com.gantzgulch.lego.api.port.OutputPort;
import com.gantzgulch.lego.common.lang.BidirectionalEnumMap;

public class BrickPiOutputPortMap extends BidirectionalEnumMap<OutputPort> {

    public static final BrickPiOutputPortMap INSTANCE = new BrickPiOutputPortMap();
    
    public BrickPiOutputPortMap() {
        
        super(OutputPort.values());
        
        add(OutputPort.PORT_A, "spi0.1:MA");
        add(OutputPort.PORT_B, "spi0.1:MB");
        add(OutputPort.PORT_C, "spi0.1:MC");
        add(OutputPort.PORT_D, "spi0.1:MD");
    }

}
