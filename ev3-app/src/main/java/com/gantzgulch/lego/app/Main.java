package com.gantzgulch.lego.app;

import java.io.IOException;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.gantzgulch.lego.device.InputPort;
import com.gantzgulch.lego.device.OutputPort;
import com.gantzgulch.lego.device.ev3.EV3ColorSensor;
import com.gantzgulch.lego.device.ev3.EV3ColorSensor.EV3ColorSensorMode;
import com.gantzgulch.lego.device.ev3.EV3GyroSensor;
import com.gantzgulch.lego.device.ev3.EV3GyroSensor.EV3GyroSensorMode;
import com.gantzgulch.lego.device.ev3.EV3LargeMotor;
import com.gantzgulch.lego.device.ev3.EV3Led;
import com.gantzgulch.lego.device.ev3.EV3Led.LedColor;
import com.gantzgulch.lego.device.ev3.EV3MediumMotor;
import com.gantzgulch.lego.device.ev3.EV3Motor.EV3MotorCommand;
import com.gantzgulch.lego.device.ev3.EV3Motor.EV3MotorState;
import com.gantzgulch.lego.device.ev3.EV3Motor.EV3MotorStopAction;
import com.gantzgulch.lego.platform.Platform;
import com.gantzgulch.lego.platform.common.DeviceFinder;
import com.gantzgulch.lego.platform.ev3.device.EV3ButtonImpl;
import com.gantzgulch.lego.shell.EV3Shell;
import com.gantzgulch.lego.tank.Tank;
import com.gantzgulch.lego.unit.Speed;
import com.gantzgulch.lego.unit.SpeedPercent;
import com.gantzgulch.lego.util.lang.Sleep;
import com.gantzgulch.lego.util.logger.EV3Logger;
import com.gantzgulch.lego.wheel.Wheels;

public class Main {

    private static final EV3Logger LOG = EV3Logger.getLogger(Main.class);

    /**
     * Identify platform.
     */
    public static void test_00() {

        final Platform platform = Platform.getInstance();

        LOG.info("Platform: %s", platform);

        LOG.info("Platform Type: %s", platform.getType());

    }

    public static void test_01() {

        final Platform platform = Platform.getInstance();

        LOG.info("Platform: %s", platform);

        final EV3Led led0Green = platform.findLed(0, LedColor.GREEN);
        final EV3Led led0Red = platform.findLed(0, LedColor.RED);

        final EV3Led led1Green = platform.findLed(1, LedColor.GREEN);
        final EV3Led led1Red = platform.findLed(1, LedColor.RED);

        led0Green.setBrightnessPercent(0);
        led0Red.setBrightnessPercent(0);

        Sleep.sleep(3, TimeUnit.SECONDS);

        led0Green.setBrightnessPercent(100);
        led0Red.setBrightnessPercent(100);

        Sleep.sleep(3, TimeUnit.SECONDS);

    }

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
        m3.setTimeSetPoint(Duration.ofSeconds(10));
        m3.setRampUpSetPoint(Duration.ofSeconds(3));
        m3.setRampDownSetPoint(Duration.ofSeconds(3));

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

        for (int i = 0; i < 100; i++) {

            Sleep.sleep(250);

            int value = cs.getValue0();

            LOG.info("test_05: value: %d", value);
        }
    }

    public static void test_06() {

        final Platform platform = Platform.getInstance();

        final EV3GyroSensor cs = platform.findDevice(EV3GyroSensor.class, InputPort.PORT_1);

        cs.setMode(EV3GyroSensorMode.GYRO_ANG);

        for (int i = 0; i < 100; i++) {

            Sleep.sleep(250);

            int value = cs.getValue0();

            LOG.info("test_06: value: %d", value);
        }
    }

    public static void test_07() {

        final Platform platform = Platform.getInstance();

        final EV3LargeMotor leftMotor = platform.findDevice(EV3LargeMotor.class, OutputPort.PORT_B);
        final EV3LargeMotor rightMotor = platform.findDevice(EV3LargeMotor.class, OutputPort.PORT_C);
        // final EV3GyroSensor gyro = platform.findDevice(EV3GyroSensor.class,
        // InputPort.PORT_1);

        for (int i = 0; i < 5; i++) {

            long now = System.currentTimeMillis();

            for (int j = 0; j < 1000; j++) {

                leftMotor.getAddress();

                leftMotor.setDutyCycleSetPoint(0);
            }

            LOG.info("getModel: loop: %d, time: %d", i, System.currentTimeMillis() - now);
        }

        // gyro.setMode(EV3GyroSensorMode.GYRO_ANG);

        final Speed speed = new SpeedPercent(30.0);

        //
        // Configure the motors
        //

        leftMotor.setSpeedSetPoint(speed);
        rightMotor.setSpeedSetPoint(speed);

        leftMotor.setStopAction(EV3MotorStopAction.BRAKE);
        rightMotor.setStopAction(EV3MotorStopAction.BRAKE);

        leftMotor.setRampUpSetPoint( Duration.ofSeconds(2));
        rightMotor.setRampUpSetPoint(Duration.ofSeconds(2));

        leftMotor.setRampDownSetPoint(Duration.ofSeconds(2));
        rightMotor.setRampDownSetPoint(Duration.ofSeconds(2));

        //
        // Set distance and start the motors
        //

        leftMotor.setPositionSetPoint(leftMotor.getCountPerRotation() * 3);
        rightMotor.setPositionSetPoint(rightMotor.getCountPerRotation() * 3);

        leftMotor.sendCommand(EV3MotorCommand.RUN_TO_REL_POS);
        rightMotor.sendCommand(EV3MotorCommand.RUN_TO_REL_POS);

        //
        // Display Gyro value while rolling
        //

        while (leftMotor.getState().contains(EV3MotorState.RUNNING) || rightMotor.getState().contains(EV3MotorState.RUNNING)) {
            // int value = gyro.getValue0();
            // LOG.info("test_07: angle: %d", value);
            LOG.info("test_07: running...");
            Sleep.sleep(100, TimeUnit.MILLISECONDS);
        }

        Sleep.sleep(500);

        //
        // Set distance and start the motors
        //

        leftMotor.setPositionSetPoint(leftMotor.getCountPerRotation() * -3);
        rightMotor.setPositionSetPoint(rightMotor.getCountPerRotation() * -3);

        leftMotor.sendCommand(EV3MotorCommand.RUN_TO_REL_POS);
        rightMotor.sendCommand(EV3MotorCommand.RUN_TO_REL_POS);

        //
        // Display Gyro value while rolling
        //

        while (leftMotor.getState().contains(EV3MotorState.RUNNING) || rightMotor.getState().contains(EV3MotorState.RUNNING)) {
            // int value = gyro.getValue0();
            // LOG.info("test_07: angle: %d", value);
            LOG.info("test_07: running 2...");
            Sleep.sleep(100, TimeUnit.MILLISECONDS);
        }

    }

    public static void test_08() {

        try {

            LOG.info("test_08: creating button.");

            final EV3ButtonImpl b = new EV3ButtonImpl();

            LOG.info("test_08: waiting.");

            Sleep.sleep(30000);

        } catch (final IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void test_09() {
        
        final Platform platform = Platform.getInstance();

        final EV3LargeMotor leftMotor = platform.findDevice(EV3LargeMotor.class, OutputPort.PORT_B);
        
        final EV3LargeMotor rightMotor = platform.findDevice(EV3LargeMotor.class, OutputPort.PORT_C);

        final Tank tank = Tank.create(leftMotor, rightMotor, Wheels.EV3EducationTire);

        final EV3Shell shell = new EV3Shell(tank);

        warmUp(leftMotor);
        
        shell.run();
    }

    private static void warmUp(final EV3LargeMotor motor) {
    
        for (int i = 0; i < 5; i++) {

            long now = System.currentTimeMillis();

            for (int j = 0; j < 1000; j++) {

                motor.getAddress();

                motor.setDutyCycleSetPoint(0);
            }

            LOG.info("getModel: loop: %d, time: %d", i, System.currentTimeMillis() - now);
        }

    }

    public static void main(final String[] args) {

        LOG.info("Running...");

        try {

            test_09();

        } finally {
            // Closeables.close(Platform.getInstance());
        }

    }

}
