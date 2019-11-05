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
package com.gantzgulch.lego.ev3dev.device;

import static com.gantzgulch.lego.ev3dev.attribute.AttributeFactory.createAttribute;

import java.nio.file.Files;
import java.nio.file.Path;

import com.gantzgulch.lego.api.device.Board;
import com.gantzgulch.lego.common.lang.Closeables;
import com.gantzgulch.lego.common.lang.StringUtil;
import com.gantzgulch.lego.common.logger.EV3Logger;
import com.gantzgulch.lego.ev3dev.attribute.Attribute;
import com.gantzgulch.lego.ev3dev.attribute.AttributeType;

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
        this.uevent = createAttribute(AttributeType.READ_ONLY, false, sysFsPath, "uevent");
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
    
    @Override
    public boolean existsAndIsModel(final String regex) {

        LOG.finest("boardExistsAndIsModel: enter");

        try {

            if ( exists() ) {

                final String model = getModel();

                LOG.finest("boardExistsAndIsModel: model: %s, regex: %s", model, regex);

                if (model.matches(regex)) {

                    LOG.finest("boardExistsAndIsModel: matches!");

                    return true;
                    
                } else {
                    
                    LOG.finest("boardExistsAndIsModel: no match.");
                    
                }

            }

        } catch (final RuntimeException e) {
            LOG.warning(e, "boardExistsAndIsModel: Unexpected exception: %s", e.getMessage());
        }

        return false;
    }

}
