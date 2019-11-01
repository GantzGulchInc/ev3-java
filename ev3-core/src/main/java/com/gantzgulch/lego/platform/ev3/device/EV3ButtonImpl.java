package com.gantzgulch.lego.platform.ev3.device;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

import com.gantzgulch.lego.device.EV3Button;
import com.gantzgulch.lego.util.logger.EV3Logger;

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
