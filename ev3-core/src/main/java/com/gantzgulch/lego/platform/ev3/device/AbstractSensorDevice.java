package com.gantzgulch.lego.platform.ev3.device;

import java.nio.file.Path;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.gantzgulch.lego.device.ev3.EV3Sensor;
import com.gantzgulch.lego.platform.impl.Attribute;
import com.gantzgulch.lego.platform.impl.AttributeType;
import com.gantzgulch.lego.util.BidirectionalEnumMap;

public class AbstractSensorDevice<C extends Enum<?>, M extends Enum<?>> extends AbstractInputDevice<C> implements EV3Sensor<C, M> {

    private final BidirectionalEnumMap<EV3SensorBinFormat> binFormatMap;

    private final BidirectionalEnumMap<M> modeMap;

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
            final BidirectionalEnumMap<C> commandMap, //
            final BidirectionalEnumMap<EV3SensorBinFormat> binFormatMap, //
            final BidirectionalEnumMap<M> modeMap) {
        
        super(sysFsPath, commandMap);
        this.binFormatMap = binFormatMap;
        this.modeMap = modeMap;
        
        this.binData = new Attribute(AttributeType.READ_ONLY, sysFsPath, "bin_data");
        this.binDataFormat = new Attribute(AttributeType.READ_ONLY, sysFsPath, "bin_data_format");
        this.decimals = new Attribute(AttributeType.READ_ONLY, sysFsPath, "decimals");
        this.fwVersion = new Attribute(AttributeType.READ_ONLY, sysFsPath, "fw_version");
        this.mode = new Attribute(AttributeType.READ_WRITE, sysFsPath, "mode");
        this.modes = new Attribute(AttributeType.READ_ONLY, sysFsPath, "modes");
        this.numValues = new Attribute(AttributeType.READ_ONLY, sysFsPath, "num_values");
        this.pollMillis = new Attribute(AttributeType.READ_WRITE, sysFsPath, "poll_ms");
        this.units = new Attribute(AttributeType.READ_ONLY, sysFsPath, "units");
        this.value0 = new Attribute(AttributeType.READ_ONLY, sysFsPath, "value0");
        this.value1 = new Attribute(AttributeType.READ_ONLY, sysFsPath, "value1");
        this.value2 = new Attribute(AttributeType.READ_ONLY, sysFsPath, "value2");
        this.value3 = new Attribute(AttributeType.READ_ONLY, sysFsPath, "value3");
        this.value4 = new Attribute(AttributeType.READ_ONLY, sysFsPath, "value4");
        this.value5 = new Attribute(AttributeType.READ_ONLY, sysFsPath, "value5");
        this.value6 = new Attribute(AttributeType.READ_ONLY, sysFsPath, "value6");
        this.value7 = new Attribute(AttributeType.READ_ONLY, sysFsPath, "value7");
        
    }

    @Override
    public byte[] getBinData() {
        return null;
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
    public M getMode() {
        return modeMap.get(mode.readString()).orElse(null);
    }
    
    @Override
    public void setMode(final M newMode) {
        mode.writeEnum(newMode, modeMap);
    }
    
    @Override
    public Set<M> getModes(){
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
    public void setPollMillis(long timeUnit, TimeUnit unit) {
        pollMillis.writeInteger( (int) unit.toMillis(timeUnit));
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
        return ( (double) value ) / Math.pow(10.0, decimals.readInteger().orElse(0));
    }
}
