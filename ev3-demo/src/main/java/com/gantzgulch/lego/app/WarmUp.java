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
package com.gantzgulch.lego.app;

import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.gantzgulch.lego.common.logger.EV3Logger;
import com.gantzgulch.lego.ev3dev.attribute.Attribute;
import com.gantzgulch.lego.ev3dev.attribute.AttributeFactory;
import com.gantzgulch.lego.ev3dev.attribute.AttributeType;

public class WarmUp implements Runnable, Closeable {

    private static final EV3Logger LOG = EV3Logger.getLogger(WarmUp.class);

    public WarmUp() {

    }

    public void run() {


        try {
            
            final Path attrPath = Files.createTempFile("attr.", ".warmUp");

            LOG.fine("run: Warming up with: %s", attrPath);
            
            final Attribute attr = AttributeFactory.createAttribute(AttributeType.READ_WRITE, false, attrPath);

            for (int i = 0; i < 5; i++) {

                long now = System.currentTimeMillis();

                for (int j = 0; j < 1000; j++) {

                    attr.writeInteger(40000);

                    attr.readInteger();
                }

                LOG.info("rum: results: loop: %d, time: %d", i, System.currentTimeMillis() - now);
            }
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }

    }
    
    @Override
    public void close() {
        
    }

    public static void main(final String[] args) {

        try( final WarmUp warmUp = new WarmUp() ) {
        
            warmUp.run();

        }
    }
}
