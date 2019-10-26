package com.gantzgulch.lego.platform.device;

import java.nio.file.Files;
import java.nio.file.Path;

import com.gantzgulch.lego.device.Board;
import com.gantzgulch.lego.logging.EV3Logger;
import com.gantzgulch.lego.platform.impl.Attribute;
import com.gantzgulch.lego.platform.impl.AttributeType;
import com.gantzgulch.lego.util.Closeables;
import com.gantzgulch.lego.util.StringUtil;

public class BoardImpl implements Board {

    public static final String ATTR_UEVENT = "uevent";

    public static final String BOARD_INFO_HW_REV = "BOARD_INFO_HW_REV";
    public static final String BOARD_INFO_MODEL = "BOARD_INFO_MODEL";
    public static final String BOARD_INFO_ROM_REV = "BOARD_INFO_ROM_REV";
    public static final String BOARD_INFO_SERIAL_NUM = "BOARD_INFO_SERIAL_NUM";
    public static final String BOARD_INFO_TYPE = "BOARD_INFO_TYPE";

    @SuppressWarnings("unused")
    private static final EV3Logger LOG = EV3Logger.getLogger(BoardImpl.class);

    private final Path sysFsPath;

    private final Attribute uevent;

    public BoardImpl(final Path sysFsPath) {
        this.sysFsPath = sysFsPath;
        this.uevent = new Attribute(AttributeType.READ_ONLY, false, sysFsPath, "uevent");
    }

    @Override
    public void close() {
        Closeables.close(uevent);
    }

    @Override
    public boolean exists() {
        return Files.isDirectory(sysFsPath);
    }
    
    
    @Override
    public String getHwRevision() {
        return StringUtil.parseProperties(uevent.readString()).getProperty(BOARD_INFO_HW_REV);
    }

    @Override
    public String getModel() {
        return StringUtil.parseProperties(uevent.readString()).getProperty(BOARD_INFO_MODEL);
    }

    @Override
    public String getRomRevision() {
        return StringUtil.parseProperties(uevent.readString()).getProperty(BOARD_INFO_ROM_REV);
    }

    @Override
    public String getSerialNumber() {
        return StringUtil.parseProperties(uevent.readString()).getProperty(BOARD_INFO_SERIAL_NUM);
    }

    @Override
    public String getType() {
        return StringUtil.parseProperties(uevent.readString()).getProperty(BOARD_INFO_TYPE);
    }

}
