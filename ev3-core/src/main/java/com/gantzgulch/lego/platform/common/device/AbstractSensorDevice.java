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
package com.gantzgulch.lego.platform.common.device;

import static com.gantzgulch.lego.platform.common.AttributeFactory.createAttribute;

import java.nio.file.Path;
import java.time.Duration;
import java.util.Set;

import com.gantzgulch.lego.device.ev3.EV3Sensor;
import com.gantzgulch.lego.platform.common.Attribute;
import com.gantzgulch.lego.platform.common.AttributeType;
import com.gantzgulch.lego.util.lang.BidirectionalEnumMap;
import com.gantzgulch.lego.util.lang.Closeables;

public class AbstractSensorDevice<CMDS extends Enum<?>, MODES extends Enum<?>> extends AbstractInputDevice<CMDS>
        implements EV3Sensor<CMDS, MODES> {

    public static final String ATTR_BIN_DATA = "bin_data";
    public static final String ATTR_BIN_DATA_FORMAT = "bin_data_format";
    public static final String ATTR_DECIMALS = "decimals";
    public static final String ATTR_FW_VERSION = "fw_version";
    public static final String ATTR_MODE = "mode";
    public static final String ATTR_MODES = "modes";
    public static final String ATTR_NUM_VALUES = "num_values";
    public static final String ATTR_POLL_MS = "poll_ms";
    public static final String ATTR_UNITS = "units";
    public static final String ATTR_VALUE0 = "value0";
    public static final String ATTR_VALUE1 = "value1";
    public static final String ATTR_VALUE2 = "value2";
    public static final String ATTR_VALUE3 = "value3";
    public static final String ATTR_VALUE4 = "value4";
    public static final String ATTR_VALUE5 = "value5";
    public static final String ATTR_VALUE6 = "value6";
    public static final String ATTR_VALUE7 = "value7";

    private final BidirectionalEnumMap<EV3SensorBinFormat> binFormatMap;
    private final BidirectionalEnumMap<MODES> modeMap;

    private final Attribute binData;
    private final Attribute binDataFormat;
    private final Attribute decimals;
    private final Attribute fwVersion;
    private final Attribute mode;
    private final Attribute modes;
    private final Attribute numValues;
    private final Attribute pollMillis;
    private final Attribute units;
    private final Attribute value0;
    private final Attribute value1;
    private final Attribute value2;
    private final Attribute value3;
    private final Attribute value4;
    private final Attribute value5;
    private final Attribute value6;
    private final Attribute value7;

    public AbstractSensorDevice(//
            final Path sysFsPath, //
            final BidirectionalEnumMap<CMDS> commandMap, //
            final BidirectionalEnumMap<EV3SensorBinFormat> binFormatMap, //
            final BidirectionalEnumMap<MODES> modeMap) {

        super(sysFsPath, commandMap);

        this.binFormatMap = binFormatMap;
        this.modeMap = modeMap;
        this.binData = createAttribute(AttributeType.READ_ONLY, false, sysFsPath, ATTR_BIN_DATA);
        this.binDataFormat = createAttribute(AttributeType.READ_ONLY, false, sysFsPath, ATTR_BIN_DATA_FORMAT);
        this.decimals = createAttribute(AttributeType.READ_ONLY, false, sysFsPath, ATTR_DECIMALS);
        this.fwVersion = createAttribute(AttributeType.READ_ONLY, false, sysFsPath, ATTR_FW_VERSION);
        this.mode = createAttribute(AttributeType.READ_WRITE, false, sysFsPath, ATTR_MODE);
        this.modes = createAttribute(AttributeType.READ_ONLY, false, sysFsPath, ATTR_MODES);
        this.numValues = createAttribute(AttributeType.READ_ONLY, false, sysFsPath, ATTR_NUM_VALUES);
        this.pollMillis = createAttribute(AttributeType.READ_WRITE, false, sysFsPath, ATTR_POLL_MS);
        this.units = createAttribute(AttributeType.READ_ONLY, false, sysFsPath, ATTR_UNITS);
        this.value0 = createAttribute(AttributeType.READ_ONLY, false, sysFsPath, ATTR_VALUE0);
        this.value1 = createAttribute(AttributeType.READ_ONLY, false, sysFsPath, ATTR_VALUE1);
        this.value2 = createAttribute(AttributeType.READ_ONLY, false, sysFsPath, ATTR_VALUE2);
        this.value3 = createAttribute(AttributeType.READ_ONLY, false, sysFsPath, ATTR_VALUE3);
        this.value4 = createAttribute(AttributeType.READ_ONLY, false, sysFsPath, ATTR_VALUE4);
        this.value5 = createAttribute(AttributeType.READ_ONLY, false, sysFsPath, ATTR_VALUE5);
        this.value6 = createAttribute(AttributeType.READ_ONLY, false, sysFsPath, ATTR_VALUE6);
        this.value7 = createAttribute(AttributeType.READ_ONLY, false, sysFsPath, ATTR_VALUE7);

    }

    @Override
    public void close() {

        Closeables.close(binData);
        Closeables.close(binDataFormat);
        Closeables.close(decimals);
        Closeables.close(fwVersion);
        Closeables.close(mode);
        Closeables.close(modes);
        Closeables.close(numValues);
        Closeables.close(pollMillis);
        Closeables.close(units);
        Closeables.close(value0);
        Closeables.close(value1);
        Closeables.close(value2);
        Closeables.close(value3);
        Closeables.close(value4);
        Closeables.close(value5);
        Closeables.close(value6);
        Closeables.close(value7);

        super.close();
    }

    @Override
    public byte[] getBinData() {
        return binData.readBytes();
    }

    @Override
    public EV3SensorBinFormat getBinDataFormat() {
        return binFormatMap.get(binDataFormat.readString()).orElse(null);
    }

    @Override
    public String getFwVersion() {
        return fwVersion.readString();
    }

    @Override
    public MODES getMode() {
        return modeMap.get(mode.readString()).orElse(null);
    }

    @Override
    public void setMode(final MODES newMode) {
        mode.writeEnum(newMode, modeMap);
    }

    @Override
    public Set<MODES> getModes() {
        return modeMap.get(modes.readStringArray());
    }

    @Override
    public int getNumValues() {
        return numValues.readInteger().orElse(0);
    }

    @Override
    public int getPollMillis() {
        return pollMillis.readInteger().orElse(0);
    }

    @Override
    public void setPollMillis(final Duration duration) {
        pollMillis.writeInteger((int) duration.toMillis());
    }

    @Override
    public String getUnits() {
        return units.readString();
    }

    @Override
    public int getValue0() {
        return value0.readInteger().orElse(0);
    }

    @Override
    public double getValue0_double() {
        return convertDouble(value0.readInteger().orElse(0));
    }

    @Override
    public int getValue1() {
        return value1.readInteger().orElse(0);
    }

    @Override
    public double getValue1_double() {
        return convertDouble(value1.readInteger().orElse(0));
    }

    @Override
    public int getValue2() {
        return value2.readInteger().orElse(0);
    }

    @Override
    public double getValue2_double() {
        return convertDouble(value2.readInteger().orElse(0));
    }

    @Override
    public int getValue3() {
        return value3.readInteger().orElse(0);
    }

    @Override
    public double getValue3_double() {
        return convertDouble(value3.readInteger().orElse(0));
    }

    @Override
    public int getValue4() {
        return value4.readInteger().orElse(0);
    }

    @Override
    public double getValue4_double() {
        return convertDouble(value4.readInteger().orElse(0));
    }

    @Override
    public int getValue5() {
        return value5.readInteger().orElse(0);
    }

    @Override
    public double getValue5_double() {
        return convertDouble(value5.readInteger().orElse(0));
    }

    @Override
    public int getValue6() {
        return value6.readInteger().orElse(0);
    }

    @Override
    public double getValue6_double() {
        return convertDouble(value6.readInteger().orElse(0));
    }

    @Override
    public int getValue7() {
        return value7.readInteger().orElse(0);
    }

    @Override
    public double getValue7_double() {
        return convertDouble(value7.readInteger().orElse(0));
    }

    private double convertDouble(final int value) {
        return ((double) value) / Math.pow(10.0, decimals.readInteger().orElse(0));
    }
}
