package com.gantzgulch.ev3.platform.ev3.device;

import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.gantzgulch.ev3.device.EV3MotorCommand;
import com.gantzgulch.ev3.device.EV3MotorState;
import com.gantzgulch.ev3.device.EV3MotorStopAction;
import com.gantzgulch.ev3.device.EV3TachoMotor;
import com.gantzgulch.ev3.device.impl.Attribute;
import com.gantzgulch.ev3.device.impl.AttributeType;
import com.gantzgulch.ev3.util.BidirectionalEnumMap;

public abstract class AbstractTachoMotor extends AbstractDevice<EV3MotorCommand> implements EV3TachoMotor<EV3MotorCommand> {

    private final BidirectionalEnumMap<EV3MotorStopAction> stopActionMap;
    private final BidirectionalEnumMap<EV3MotorState> stateMap;
    
    private final Attribute countPerRotation;
    private final Attribute dutyCycle;
    private final Attribute dutyCycleSetPoint;
    private final Attribute polarity;
    private final Attribute position;
    private final Attribute holdPidKd;
    private final Attribute holdPidKi;
    private final Attribute holdPidKp;
    private final Attribute maxSpeed;
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
            final BidirectionalEnumMap<EV3MotorState> stateMap) {

        super(sysFsPath, commandMap);
        
        this.stopActionMap = stopActionMap;
        this.stateMap = stateMap;
    
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
    public Set<EV3MotorStopAction> getStopActions() {
        
        final String[] actions = stopActions.readStringArray();

        return stopActionMap.get(actions);
    }
    
    @Override
    public void setStopAction(final EV3MotorStopAction stopAction) {
        
        Optional<String> action = stopActionMap.get(stopAction);
        
        if( action.isPresent() ) {
            this.stopAction.writeString(action.get());
        }else {
            throw new UnsupportedOperationException("Unsupported stop action: " + stopAction);
        }
        
    }
    
    @Override
    public int getMaxSpeed() {
        return this.maxSpeed.readInteger().orElse(0);
    }
    
    @Override
    public int getPosition() {
        return this.position.readInteger().orElse(0);
    }
    
    @Override
    public void setSpeedSetPoint(int speed) {
        this.speedSetPoint.writeInteger(speed);
    }
    
    @Override
    public void setRampUpSetPoint(final long timeUnit, final TimeUnit unit) {
        
        this.rampUpSetPoint.writeInteger( (int) unit.toMillis(timeUnit) );
        
    }
    
    @Override
    public void setRampDownSetPoint(final long timeUnit, final TimeUnit unit) {
        
        this.rampDownSetPoint.writeInteger( (int) unit.toMillis(timeUnit) );
        
    }
    
    @Override
    public void setTimeSetPoint(final long timeUnit, final TimeUnit unit) {
        this.timeSetPoint.writeInteger( (int) unit.toMillis(timeUnit)); 
    }
    
    @Override
    public Set<EV3MotorState> getState() {
        return stateMap.get(this.state.readStringArray() );
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
