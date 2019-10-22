package com.gantzgulch.lego.app;

import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.gantzgulch.lego.device.Board;
import com.gantzgulch.lego.device.ev3.EV3ColorSensor;
import com.gantzgulch.lego.device.ev3.EV3ColorSensor.EV3ColorSensorMode;
import com.gantzgulch.lego.device.ev3.EV3GyroSensor;
import com.gantzgulch.lego.device.ev3.EV3GyroSensor.EV3GyroSensorMode;
import com.gantzgulch.lego.device.ev3.EV3LargeMotor;
import com.gantzgulch.lego.device.ev3.EV3MediumMotor;
import com.gantzgulch.lego.device.ev3.EV3Motor.EV3MotorCommand;
import com.gantzgulch.lego.device.ev3.EV3Motor.EV3MotorState;
import com.gantzgulch.lego.device.ev3.EV3Motor.EV3MotorStopAction;
import com.gantzgulch.lego.logging.EV3Logger;
import com.gantzgulch.lego.platform.Platform;
import com.gantzgulch.lego.platform.ev3.DeviceFinder;
import com.gantzgulch.lego.port.InputPort;
import com.gantzgulch.lego.port.OutputPort;
import com.gantzgulch.lego.unit.Speed;
import com.gantzgulch.lego.unit.SpeedPercent;
import com.gantzgulch.lego.util.Sleep;

public class Main {

    private static final EV3Logger LOG = EV3Logger.getLogger(Main.class);

//    public static void test_01() {
//
//        final Platform platform = Platform.getInstance();
//
//        LOG.info("Platform: %s", platform);
//
//        final LedPair led = platform.getLedPair(LedPairId.LED_0);
//
//        final LedPair led2 = platform.getLedPair(LedPairId.LED_0);
//
//        if (led == led2) {
//            LOG.info("They are the same devices.");
//        } else {
//            LOG.info("They are NOT the same devices.");
//        }
//
//        LOG.info("Led: setBrightness: %d,%d", 0, 0);
//
//        led.setBrightness(0, 0);
//
//        Sleep.sleep(5000);
//
//        LOG.info("Led: setBrightness: %d,%d", 0, led.getMaxBrightness1());
//
//        led.setBrightness(0, led.getMaxBrightness1());
//
//        Sleep.sleep(5000);
//
//        LOG.info("Led: setBrightness: %d,%d", led.getMaxBrightness0(), 0);
//
//        led.setBrightness(led.getMaxBrightness0(), 0);
//
//        Sleep.sleep(5000);
//
//        LOG.info("Led: setBrightness: %d,%d", led.getMaxBrightness0(), led.getMaxBrightness1());
//
//        led.setBrightness(led.getMaxBrightness0(), led.getMaxBrightness1());
//
//        Sleep.sleep(5000);
//
//        LOG.info("Led: setBrightness: %d,%d", 0, 0);
//
//        led.setBrightness(0, 0);
//
//    }

    public static void test_02() {

        final DeviceFinder df = new DeviceFinder();

        final Optional<Path> dp1 = df.findDevicePath("lego-sensor", "lego-ev3-touch", "ev3-ports:in3");

        if (dp1.isPresent()) {
            LOG.info("Found device: %s", dp1.get());
        } else {
            LOG.info("Device NOT found.");
        }

        final Optional<Path> dp2 = df.findDevicePath("tacho-motor", "lego-ev3-l-motor", "ev3-ports:outB");

        if (dp2.isPresent()) {
            LOG.info("Found device: %s", dp2.get());
        } else {
            LOG.info("Device NOT found.");
        }

    }

    public static void test_03() {

        final Platform platform = Platform.getInstance();

        LOG.info("Platform: %s", platform);

        final EV3LargeMotor m1 = platform.findDevice(EV3LargeMotor.class, OutputPort.PORT_B);

        LOG.info("test_03: m1: \n%s", m1);

        final EV3LargeMotor m2 = platform.findDevice(EV3LargeMotor.class, OutputPort.PORT_C);

        LOG.info("test_03: m2: \n%s", m2);

        final EV3MediumMotor m3 = platform.findDevice(EV3MediumMotor.class, OutputPort.PORT_D);

        LOG.info("test_03: m3: \n%s", m3);

        m3.setStopAction(EV3MotorStopAction.BRAKE);

        LOG.info("test_03: m3: \n%s", m3);
    }

    public static void test_04() {

        final Platform platform = Platform.getInstance();

        final EV3MediumMotor m3 = platform.findDevice(EV3MediumMotor.class, OutputPort.PORT_D);

        m3.setStopAction(EV3MotorStopAction.BRAKE);
        m3.setSpeedSetPoint(m3.getMaxSpeed() / 3);
        m3.setTimeSetPoint(10, TimeUnit.SECONDS);
        m3.setRampUpSetPoint(3, TimeUnit.SECONDS);
        m3.setRampDownSetPoint(3, TimeUnit.SECONDS);

        Set<EV3MotorState> state;
        int position;
        int loop = 0;

        for (int i = 0; i < 15; i++) {

            loop = 0;
            
            m3.sendCommand(EV3MotorCommand.RUN_TIMED);

            do {
                state = m3.getState();

                // position = m3.getPosition();

                loop += 1;

                // LOG.info("test_03: m3: state: %s, postition: %d", state, position);

            } while (state.contains(EV3MotorState.RUNNING));

            position = m3.getPosition();

            LOG.info("test_03: m3: loop: %d, state: %s, postition: %d", loop, state, position);

        }
    }

    public static void test_05() {

        final Platform platform = Platform.getInstance();

        final EV3ColorSensor cs = platform.findDevice(EV3ColorSensor.class, InputPort.PORT_4);

        cs.setMode(EV3ColorSensorMode.COL_REFLECT);
        
        for(int i=0; i<100; i++) {
            
            Sleep.sleep(250);
            
            int value = cs.getValue0();
            
            LOG.info("test_05: value: %d", value);
        }
    }

    public static void test_06() {

        final Platform platform = Platform.getInstance();

        final EV3GyroSensor cs = platform.findDevice(EV3GyroSensor.class, InputPort.PORT_1);

        cs.setMode(EV3GyroSensorMode.GYRO_ANG);
        
        for(int i=0; i<100; i++) {
            
            Sleep.sleep(250);
            
            int value = cs.getValue0();
            
            LOG.info("test_06: value: %d", value);
        }
    }

    public static void test_07() {

        final Platform platform = Platform.getInstance();
        final Board board = platform.getBoard();
        
        
        final EV3LargeMotor leftMotor = platform.findDevice(EV3LargeMotor.class, OutputPort.PORT_B);
        final EV3LargeMotor rightMotor = platform.findDevice(EV3LargeMotor.class, OutputPort.PORT_C);
        final EV3GyroSensor gyro = platform.findDevice(EV3GyroSensor.class, InputPort.PORT_1);

        for(int i=0; i<5; i++) {
            
            long now = System.currentTimeMillis();
            
            for(int j=0; j<1000; j++) {
                board.getModel();
                leftMotor.setDutyCycleSetPoint(0);
            }
            
            LOG.info("getModel: loop: %d, time: %d", i, System.currentTimeMillis() - now );
        }

        
        
        
        
        gyro.setMode(EV3GyroSensorMode.GYRO_ANG);
        
        final Speed speed = new SpeedPercent(30.0);

        leftMotor.setSpeedSetPoint(speed);
        rightMotor.setSpeedSetPoint(speed);
        
        leftMotor.setStopAction(EV3MotorStopAction.BRAKE);
        rightMotor.setStopAction(EV3MotorStopAction.BRAKE);
        
        leftMotor.setPositionSetPoint( leftMotor.getCountPerRotation() * 3);
        rightMotor.setPositionSetPoint( rightMotor.getCountPerRotation() * 3);

        leftMotor.setRampUpSetPoint(1000, TimeUnit.MILLISECONDS);
        rightMotor.setRampUpSetPoint(1000, TimeUnit.MILLISECONDS);

        //
        // Start the motors
        //
        
        leftMotor.sendCommand(EV3MotorCommand.RUN_TO_REL_POS);
        rightMotor.sendCommand(EV3MotorCommand.RUN_TO_REL_POS);
        
        // 
        // Display Gyro value while rolling
        //
        
        while( leftMotor.getState().contains(EV3MotorState.RUNNING) && rightMotor.getState().contains(EV3MotorState.RUNNING)) {
            int value = gyro.getValue0();
            LOG.info("test_07: angle: %d", value);
            Sleep.sleep(100, TimeUnit.MILLISECONDS);
        }

        leftMotor.setPositionSetPoint( leftMotor.getCountPerRotation() * -3);
        rightMotor.setPositionSetPoint( rightMotor.getCountPerRotation() * -3);
        
        Sleep.sleep(500);
        
        //
        // Start the motors
        //

        leftMotor.sendCommand(EV3MotorCommand.RUN_TO_REL_POS);
        rightMotor.sendCommand(EV3MotorCommand.RUN_TO_REL_POS);

        // 
        // Display Gyro value while rolling
        //

        while( leftMotor.getState().contains(EV3MotorState.RUNNING) && rightMotor.getState().contains(EV3MotorState.RUNNING)) {
            int value = gyro.getValue0();
            LOG.info("test_07: angle: %d", value);
            Sleep.sleep(100, TimeUnit.MILLISECONDS);
        }

//        final MotorWrapper leftWrapper = MotorWrapper.create(leftMotor);
//        final MotorWrapper rightWrapper = MotorWrapper.create(rightMotor);
//        
//        final Speed speed = new SpeedPercent(30.0);
//        
//        leftWrapper.setSpeed(speed);
//        rightWrapper.setSpeed(speed);
//        leftWrapper.setBrake(true);
//        rightWrapper.setBrake(true);
//        
//        leftWrapper.onForRotations(3, false);
//        rightWrapper.onForRotations(3, false);
        
    }


    
    public static void main(final String[] args) {

        LOG.info("Running...");
        
        test_07();

    }

}
