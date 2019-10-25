package com.gantzgulch.lego.platform.device;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Path;
import java.util.Properties;

import com.gantzgulch.lego.device.Board;
import com.gantzgulch.lego.logging.EV3Logger;
import com.gantzgulch.lego.platform.impl.Attribute;
import com.gantzgulch.lego.platform.impl.AttributeType;
import com.gantzgulch.lego.util.Closeables;

public class BoardImpl implements Board {

    public static final String ATTR_UEVENT = "uevent";

    public static final String BOARD_INFO_HW_REV = "BOARD_INFO_HW_REV";
    public static final String BOARD_INFO_MODEL = "BOARD_INFO_MODEL";
    public static final String BOARD_INFO_ROM_REV = "BOARD_INFO_ROM_REV";
    public static final String BOARD_INFO_SERIAL_NUM = "BOARD_INFO_SERIAL_NUM";
    public static final String BOARD_INFO_TYPE = "BOARD_INFO_TYPE";

    private static final EV3Logger LOG = EV3Logger.getLogger(BoardImpl.class);

    private final Attribute uevent;

    public BoardImpl(final Path sysFsPath) {
        this.uevent = new Attribute(AttributeType.READ_ONLY, false, sysFsPath, "uevent");
    }

    @Override
    public void close() {
        Closeables.close(uevent);
    }

    @Override
    public String getHwRevision() {
        return parseProperties(uevent.readString()).getProperty(BOARD_INFO_HW_REV);
    }

    @Override
    public String getModel() {
        return parseProperties(uevent.readString()).getProperty(BOARD_INFO_MODEL);
    }

    @Override
    public String getRomRevision() {
        return parseProperties(uevent.readString()).getProperty(BOARD_INFO_ROM_REV);
    }

    @Override
    public String getSerialNumber() {
        return parseProperties(uevent.readString()).getProperty(BOARD_INFO_SERIAL_NUM);
    }

    @Override
    public String getType() {
        return parseProperties(uevent.readString()).getProperty(BOARD_INFO_TYPE);
    }

    private Properties parseProperties(final String value) {

        final Properties props = new Properties();

        try {

            props.load(new StringReader(value));

        } catch (final IOException e) {
            LOG.warning(e, "Error parsing board properties.");
        }

        return props;
    }

}
