package com.gantzgulch.lego.platform.common;

import java.nio.file.Path;

public final class AttributeFactory {

    private AttributeFactory() {

    }

    public static Attribute createAttribute(final AttributeType type, boolean preWarm, final Path sysPath) {
        return new AttributeFileChannel(type, preWarm, sysPath);
    }

    public static Attribute createAttribute(final AttributeType type, boolean preWarm, final Path sysPath, final String... attributeName) {
        return new AttributeFileChannel(type, preWarm, sysPath, attributeName);
    }

}
