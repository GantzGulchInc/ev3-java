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
package com.gantzgulch.lego.ev3dev.device.ev3;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

import com.gantzgulch.lego.api.device.ev3.EV3Button;
import com.gantzgulch.lego.common.logger.EV3Logger;

public class EV3ButtonImpl implements EV3Button, Runnable {

    private static final EV3Logger LOG = EV3Logger.getLogger(EV3ButtonImpl.class);

    private static final Path EVENT_PATH = Path.of("/dev/input/by-path/platform-gpio_keys-event");

    private final InputStream eventStream;

    private final BufferedInputStream bufferedStream;

    private final DataInputStream dis;

    private final Thread thread;

    public EV3ButtonImpl() throws IOException {

        this.eventStream = new FileInputStream(EVENT_PATH.toFile());

        this.bufferedStream = new BufferedInputStream(this.eventStream);

        this.dis = new DataInputStream(this.bufferedStream);

        this.thread = new Thread(this);

        this.thread.start();
    }

    @Override
    public void close() throws IOException {

    }

    private int cshort(final byte b1, final byte b2) {

        return (int) (//
        ((b2 & 0x00FF) << 8) | //
                ((b1 & 0x00FF)) //
        );

    }

    private int cint(final byte b1, final byte b2, final byte b3, final byte b4) {

        return (int) ( //
        ((b4 & 0x00FF) << 24) | //
                ((b3 & 0x00FF) << 16) | //
                ((b2 & 0x00FF) << 8) | //
                ((b1 & 0x00FF)) //
        );

    }

    @Override
    public void run() {

        while (true) {

            try {

                long ts1 = this.dis.readLong(); // Total 16 bytes for ts
                int type = cshort(this.dis.readByte(), this.dis.readByte());
                int code = cshort(this.dis.readByte(), this.dis.readByte());
                int value = cint(this.dis.readByte(), this.dis.readByte(), this.dis.readByte(), this.dis.readByte());

                LOG.info("run: type: %d   code: %d  value: %d", type, code, value);

            } catch (final IOException e) {

                LOG.warning(e, "Unexpected exception reading stream: " + e.getMessage());

                return;
            }

        }
    }
}
