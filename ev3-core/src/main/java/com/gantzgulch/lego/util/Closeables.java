package com.gantzgulch.lego.util;

import java.io.Closeable;
import java.io.IOException;

import com.gantzgulch.lego.logging.EV3Logger;

public final class Closeables {

    private static final EV3Logger LOG = EV3Logger.getLogger(Closeables.class);

    private Closeables() {
    }

    public static void close(final Closeable closeable) {

        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (final RuntimeException | IOException e) {
            LOG.warning(e, "Error closing: %s : %s", closeable, e.getMessage());
        }
    }

    public static void close(final Closeable... closeables) {

        if (closeables != null) {
            for (final Closeable closeable : closeables) {
                close(closeable);
            }
        }
    }
}
