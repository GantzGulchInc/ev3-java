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
package com.gantzgulch.lego.app;

import java.io.IOException;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.gantzgulch.lego.api.device.ev3.EV3ColorSensor;
import com.gantzgulch.lego.api.device.ev3.EV3ColorSensorMode;
import com.gantzgulch.lego.api.device.ev3.EV3GyroSensor;
import com.gantzgulch.lego.api.device.ev3.EV3GyroSensorMode;
import com.gantzgulch.lego.api.device.ev3.EV3LargeMotor;
import com.gantzgulch.lego.api.device.ev3.EV3Led;
import com.gantzgulch.lego.api.device.ev3.EV3LedColor;
import com.gantzgulch.lego.api.device.ev3.EV3MediumMotor;
import com.gantzgulch.lego.api.device.ev3.EV3MotorCommand;
import com.gantzgulch.lego.api.device.ev3.EV3MotorState;
import com.gantzgulch.lego.api.device.ev3.EV3MotorStopAction;
import com.gantzgulch.lego.api.platform.Platform;
import com.gantzgulch.lego.api.port.InputPort;
import com.gantzgulch.lego.api.port.OutputPort;
import com.gantzgulch.lego.api.unit.Speed;
import com.gantzgulch.lego.api.unit.SpeedPercent;
import com.gantzgulch.lego.common.lang.Sleep;
import com.gantzgulch.lego.common.logger.EV3Logger;
import com.gantzgulch.lego.ev3dev.DeviceFinder;
import com.gantzgulch.lego.ev3dev.device.ev3.EV3ButtonImpl;
import com.gantzgulch.lego.ev3dev.platform.PlatformFactory;
import com.gantzgulch.lego.shell.EV3Shell;
import com.gantzgulch.lego.tank.Tank;
import com.gantzgulch.lego.wheel.Wheels;

public class Tests {

    private static final EV3Logger LOG = EV3Logger.getLogger(Tests.class);

    /**
     * Identify platform.
     */
    public static void test_00() {

        final Platform platform = PlatformFactory.INSTANCE.getPlatform();

        LOG.info("Platform: %s", platform);

        LOG.info("Platform Type: %s", platform.getType());

    }

    public static void test_01() {

        final Platform platform = PlatformFactory.INSTANCE.getPlatform();

        LOG.info("Platform: %s", platform);

        final EV3Led led0Green = platform.findLed(0, EV3LedColor.GREEN);
        final EV3Led led0Red = platform.findLed(0, EV3LedColor.RED);

        final EV3Led led1Green = platform.findLed(1, EV3LedColor.GREEN);
        final EV3Led led1Red = platform.findLed(1, EV3LedColor.RED);

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

        final Platform platform = PlatformFactory.INSTANCE.getPlatform();

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

        final Platform platform = PlatformFactory.INSTANCE.getPlatform();

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

        final Platform platform = PlatformFactory.INSTANCE.getPlatform();

        final EV3ColorSensor cs = platform.findDevice(EV3ColorSensor.class, InputPort.PORT_4);

        cs.setMode(EV3ColorSensorMode.COL_REFLECT);

        for (int i = 0; i < 100; i++) {

            Sleep.sleep(250);

            int value = cs.getValue0();

            LOG.info("test_05: value: %d", value);
        }
    }

    public static void test_06() {

        final Platform platform = PlatformFactory.INSTANCE.getPlatform();

        final EV3GyroSensor cs = platform.findDevice(EV3GyroSensor.class, InputPort.PORT_1);

        cs.setMode(EV3GyroSensorMode.GYRO_ANG);

        for (int i = 0; i < 100; i++) {

            Sleep.sleep(250);

            int value = cs.getValue0();

            LOG.info("test_06: value: %d", value);
        }
    }

    public static void test_07() {

        final Platform platform = PlatformFactory.INSTANCE.getPlatform();

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
        
        final Platform platform = PlatformFactory.INSTANCE.getPlatform();

        final EV3LargeMotor leftMotor = platform.findDevice(EV3LargeMotor.class, OutputPort.PORT_B);
        
        final EV3LargeMotor rightMotor = platform.findDevice(EV3LargeMotor.class, OutputPort.PORT_C);

        final Tank tank = Tank.create(leftMotor, rightMotor, Wheels.EV3EducationTire);

        final EV3Shell shell = new EV3Shell(tank);

        shell.run();
    }

}
