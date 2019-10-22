package com.gantzgulch.lego.platform.ev3.device;

import java.nio.file.Path;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.gantzgulch.lego.device.ev3.EV3Motor.EV3MotorCommand;
import com.gantzgulch.lego.device.ev3.EV3TachoMotor;
import com.gantzgulch.lego.platform.impl.Attribute;
import com.gantzgulch.lego.platform.impl.AttributeType;
import com.gantzgulch.lego.util.BidirectionalEnumMap;

public abstract class AbstractTachoMotor extends AbstractOutputDevice<EV3MotorCommand> implements EV3TachoMotor<EV3MotorCommand> {

    private final BidirectionalEnumMap<EV3MotorStopAction> stopActionMap;
    private final BidirectionalEnumMap<EV3MotorState> stateMap;
    private final BidirectionalEnumMap<EV3MotorPolarity> polarityMap;
    
    private final Attribute countPerRotation;
    private final Attribute dutyCycle;
    private final Attribute dutyCycleSetPoint;
    private final Attribute maxSpeed;
    private final Attribute polarity;
    private final Attribute position;
    private final Attribute holdPidKd;
    private final Attribute holdPidKi;
    private final Attribute holdPidKp;
    private final Attribute positionSetPoint;
    private final Attribute speed; // Tacho counts per second
    private final Attribute speedSetPoint;
    private final Attribute rampUpSetPoint;
    private final Attribute rampDownSetPoint;
    private final Attribute speedPidKd;
    private final Attribute speedPidKi;
    private final Attribute speedPidKp;
    private final Attribute state;
    private final Attribute stopAction;
    private final Attribute stopActions;
    private final Attribute timeSetPoint; // Milliseconds

    
    public AbstractTachoMotor(//
            final Path sysFsPath, //
            final BidirectionalEnumMap<EV3MotorCommand> commandMap, //
            final BidirectionalEnumMap<EV3MotorStopAction> stopActionMap, 
            final BidirectionalEnumMap<EV3MotorState> stateMap, 
            final BidirectionalEnumMap<EV3MotorPolarity> polarityMap) {

        super(sysFsPath, commandMap);
        
        this.stopActionMap = stopActionMap;
        this.stateMap = stateMap;
        this.polarityMap = polarityMap;
    
        this.countPerRotation = new Attribute(AttributeType.READ_ONLY, this.sysFsPath, "count_per_rot");
        this.dutyCycle = new Attribute(AttributeType.READ_ONLY, this.sysFsPath, "duty_cycle");
        this.dutyCycleSetPoint = new Attribute(AttributeType.READ_WRITE, this.sysFsPath, "duty_cycle_sp");
        this.polarity = new Attribute(AttributeType.READ_WRITE, this.sysFsPath, "polarity");
        this.position = new Attribute(AttributeType.READ_WRITE, this.sysFsPath, "position");
        this.holdPidKd = new Attribute(AttributeType.READ_WRITE, this.sysFsPath, "hold_pid","Kd");
        this.holdPidKi = new Attribute(AttributeType.READ_WRITE, this.sysFsPath, "hold_pid","Ki");
        this.holdPidKp = new Attribute(AttributeType.READ_WRITE, this.sysFsPath, "hold_pid","Kp");
        this.maxSpeed = new Attribute(AttributeType.READ_ONLY, this.sysFsPath, "max_speed");
        this.positionSetPoint = new Attribute(AttributeType.READ_WRITE, this.sysFsPath, "position_sp");
        this.speed = new Attribute(AttributeType.READ_ONLY, this.sysFsPath, "speed");
        this.speedSetPoint = new Attribute(AttributeType.READ_WRITE, this.sysFsPath, "speed_sp");
        this.rampUpSetPoint = new Attribute(AttributeType.READ_WRITE, this.sysFsPath, "ramp_up_sp");
        this.rampDownSetPoint = new Attribute(AttributeType.READ_WRITE, this.sysFsPath, "ramp_down_sp");
        this.speedPidKd = new Attribute(AttributeType.READ_WRITE, this.sysFsPath, "speed_pid","Kd");
        this.speedPidKi = new Attribute(AttributeType.READ_WRITE, this.sysFsPath, "speed_pid","Ki");
        this.speedPidKp = new Attribute(AttributeType.READ_WRITE, this.sysFsPath, "speed_pid","Kp");
        this.state = new Attribute(AttributeType.READ_ONLY, this.sysFsPath, "state");
        this.stopAction = new Attribute(AttributeType.READ_WRITE, this.sysFsPath, "stop_action");
        this.stopActions = new Attribute(AttributeType.READ_ONLY, this.sysFsPath, "stop_actions");
        this.timeSetPoint = new Attribute(AttributeType.READ_WRITE, this.sysFsPath, "time_sp");
        
    }
    
    @Override
    public int getCountPerRotation() {
        return countPerRotation.readInteger().orElse(0);
    }

    @Override
    public int getDutyCycle() {
        return dutyCycle.readInteger().orElse(0);
    }

    @Override
    public int getDutyCycleSetPoint() {
        return dutyCycleSetPoint.readInteger().orElse(0);
    }

    @Override
    public void setDutyCycleSetPoint(int newDutyCycleSetPoint) {
        dutyCycleSetPoint.writeInteger(newDutyCycleSetPoint);
    }

    @Override
    public EV3MotorPolarity getPolarity() {
        return polarity.readEnum(polarityMap).orElse(EV3MotorPolarity.NORMAL);
    }

    @Override
    public void setPolarity(final EV3MotorPolarity newPolarity) {
        polarity.writeEnum(newPolarity, polarityMap);
    }

    @Override
    public int getPosition() {
        return position.readInteger().orElse(0);
    }

    @Override
    public void setPosition(final int newPosition) {
        position.writeInteger(newPosition);
    }

    @Override
    public int getHoldPidKd() {
        return holdPidKd.readInteger().orElse(0);
    }

    @Override
    public void setHoldPidKd(final int newHoldPidKd) {
        holdPidKd.writeInteger(newHoldPidKd);
    }

    @Override
    public int getHoldPidKi() {
        return holdPidKi.readInteger().orElse(0);
    }

    @Override
    public void setHoldPidKi(final int newHoldPidKi) {
        holdPidKi.writeInteger(newHoldPidKi);
    }

    @Override
    public int getHoldPidKp() {
        return holdPidKp.readInteger().orElse(0);
    }

    @Override
    public void setHoldPidKp(final int newHoldPidKp) {
        holdPidKp.writeInteger(newHoldPidKp);
    }

    @Override
    public int getMaxSpeed() {
        return maxSpeed.readInteger().orElse(0);
    }

    @Override
    public int getPositionSetPoint() {
        return positionSetPoint.readInteger().orElse(0);
    }

    @Override
    public void setPositionSetPoint(final int newPositionSetPoint) {
        positionSetPoint.writeInteger(newPositionSetPoint);
    }

    @Override
    public int getSpeed() {
        return speed.readInteger().orElse(0);
    }

    @Override
    public int getSpeedSetPoint() {
        return speedSetPoint.readInteger().orElse(0);
    }

    @Override
    public void setSpeedSetPoint(int speed) {
        speedSetPoint.writeInteger(speed);
    }

    @Override
    public int getRampUpSetPointMillis() {
        return rampUpSetPoint.readInteger().orElse(0);
    }

    @Override
    public void setRampUpSetPoint(final long timeUnit, final TimeUnit unit) {
        rampUpSetPoint.writeInteger( (int)  unit.toMillis(timeUnit));
    }

    @Override
    public int getRampDownSetPointMillis() {
        return rampDownSetPoint.readInteger().orElse(0);
    }

    @Override
    public void setRampDownSetPoint(final long timeUnit, final TimeUnit unit) {
        rampDownSetPoint.writeInteger((int) unit.toMillis(timeUnit));
    }

    @Override
    public int getSpeedPidKd() {
        return speedPidKd.readInteger().orElse(0);
    }

    @Override
    public void setSpeedPidKd(final int newSpeedPidKd) {
        speedPidKd.writeInteger(newSpeedPidKd);
    }

    @Override
    public int getSpeedPidKi() {
        return speedPidKi.readInteger().orElse(0);
    }

    @Override
    public void setSpeedPidKi(final int newSpeedPidKi) {
        
    }

    @Override
    public int getSpeedPidKp() {
        return speedPidKp.readInteger().orElse(0);
    }

    @Override
    public void setSpeedPidKp(int newSpeedPidKp) {
        speedPidKp.writeInteger(newSpeedPidKp);
    }

    @Override
    public Set<EV3MotorState> getState(){
        return stateMap.get(this.state.readStringArray() );
    }

    @Override
    public EV3MotorStopAction getStopAction() {
        return stopActionMap.get( stopAction.readString() ).orElse(EV3MotorStopAction.BRAKE);
    }

    @Override
    public void setStopAction(final EV3MotorStopAction newStopAction) {
        stopAction.writeEnum(newStopAction, stopActionMap);
    }

    @Override
    public Set<EV3MotorStopAction> getStopActions(){
        return stopActionMap.get( stopActions.readStringArray() );
    }

    @Override
    public int getTimeSetPointMillis() {
        return timeSetPoint.readInteger().orElse(0);
    }

    @Override
    public void setTimeSetPoint(long timeUnit, TimeUnit unit) {
        timeSetPoint.writeInteger( (int)unit.toMillis(timeUnit) );
    }
    
    @Override
    public String toString() {
        
        final StringBuffer b = new StringBuffer();

        b.append("TachoMotor:\n");
        b.append("  sysFsPath .............: ").append(sysFsPath).append("\n");
        b.append("  address ...............: ").append(address.readString()).append("\n");
        b.append("  driverName ............: ").append(driverName.readString()).append("\n");
        b.append("  commands ..............: ").append(commands.readString()).append("\n");
        b.append("  countPerRotation ......: ").append(countPerRotation.readString()).append("\n");
        b.append("  dutyCycle .............: ").append(dutyCycle.readString()).append("\n");
        b.append("  dutyCycleSetPoint .....: ").append(dutyCycleSetPoint.readString()).append("\n");
        b.append("  polarity ..............: ").append(polarity.readString()).append("\n");
        b.append("  holdPidKd .............: ").append(holdPidKd.readString()).append("\n");
        b.append("  holdPidKi .............: ").append(holdPidKi.readString()).append("\n");
        b.append("  holdPidKp .............: ").append(holdPidKp.readString()).append("\n");
        b.append("  maxSpeed ..............: ").append(maxSpeed.readString()).append("\n");
        b.append("  positionSetPoint ......: ").append(positionSetPoint.readString()).append("\n");
        b.append("  speed .................: ").append(speed.readString()).append("\n");
        b.append("  speedSetPoint .........: ").append(speedSetPoint.readString()).append("\n");
        b.append("  rampUpSetPoint ........: ").append(rampUpSetPoint.readString()).append("\n");
        b.append("  rampDownSetPoint ......: ").append(rampDownSetPoint.readString()).append("\n");
        b.append("  speedPidKd ............: ").append(speedPidKd.readString()).append("\n");
        b.append("  speedPidKi ............: ").append(speedPidKi.readString()).append("\n");
        b.append("  speedPidKp ............: ").append(speedPidKp.readString()).append("\n");
        b.append("  state .................: ").append(state.readString()).append("\n");
        b.append("  stopAction ............: ").append(stopAction.readString()).append("\n");
        b.append("  stopActions ...........: ").append(stopActions.readString()).append("\n");
        b.append("  timeSetPoint ..........: ").append(timeSetPoint.readString()).append("\n");
        
        return b.toString();
    }

}
