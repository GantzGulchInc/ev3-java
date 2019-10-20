package com.gantzgulch.ev3.app;

import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.gantzgulch.ev3.device.EV3LargeMotor;
import com.gantzgulch.ev3.device.EV3MediumMotor;
import com.gantzgulch.ev3.device.EV3MotorCommand;
import com.gantzgulch.ev3.device.EV3MotorState;
import com.gantzgulch.ev3.device.EV3MotorStopAction;
import com.gantzgulch.ev3.device.LedPair;
import com.gantzgulch.ev3.device.LedPairId;
import com.gantzgulch.ev3.logging.EV3Logger;
import com.gantzgulch.ev3.platform.EV3Platform;
import com.gantzgulch.ev3.platform.ev3.DeviceFinder;
import com.gantzgulch.ev3.port.OutputPort;
import com.gantzgulch.ev3.util.Sleep;

public class Main {

    private static final EV3Logger LOG = EV3Logger.getLogger(Main.class);

    public static void test_01() {

        final EV3Platform platform = EV3Platform.getInstance();

        LOG.info("Platform: %s", platform);

        final LedPair led = platform.getLedPair(LedPairId.LED_0);

        final LedPair led2 = platform.getLedPair(LedPairId.LED_0);

        if (led == led2) {
            LOG.info("They are the same devices.");
        } else {
            LOG.info("They are NOT the same devices.");
        }

        LOG.info("Led: setBrightness: %d,%d", 0, 0);

        led.setBrightness(0, 0);

        Sleep.sleep(5000);

        LOG.info("Led: setBrightness: %d,%d", 0, led.getMaxBrightness1());

        led.setBrightness(0, led.getMaxBrightness1());

        Sleep.sleep(5000);

        LOG.info("Led: setBrightness: %d,%d", led.getMaxBrightness0(), 0);

        led.setBrightness(led.getMaxBrightness0(), 0);

        Sleep.sleep(5000);

        LOG.info("Led: setBrightness: %d,%d", led.getMaxBrightness0(), led.getMaxBrightness1());

        led.setBrightness(led.getMaxBrightness0(), led.getMaxBrightness1());

        Sleep.sleep(5000);

        LOG.info("Led: setBrightness: %d,%d", 0, 0);

        led.setBrightness(0, 0);

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

        final EV3Platform platform = EV3Platform.getInstance();

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

        final EV3Platform platform = EV3Platform.getInstance();

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
            
            m3.setCommand(EV3MotorCommand.RUN_TIMED);

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

    public static void main(final String[] args) {

        test_04();

    }

}
