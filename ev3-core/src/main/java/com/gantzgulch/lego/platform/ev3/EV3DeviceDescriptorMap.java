package com.gantzgulch.lego.platform.ev3;

import java.lang.reflect.Constructor;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.gantzgulch.lego.device.Device;
import com.gantzgulch.lego.device.InputDevice;
import com.gantzgulch.lego.device.OutputDevice;
import com.gantzgulch.lego.device.ev3.EV3ColorSensor;
import com.gantzgulch.lego.device.ev3.EV3LargeMotor;
import com.gantzgulch.lego.device.ev3.EV3MediumMotor;
import com.gantzgulch.lego.device.ev3.EV3TouchSensor;
import com.gantzgulch.lego.logging.EV3Logger;
import com.gantzgulch.lego.platform.ev3.device.EV3ColorSensorImpl;
import com.gantzgulch.lego.platform.ev3.device.EV3LargeMotorImpl;
import com.gantzgulch.lego.platform.ev3.device.EV3MediumMotorImpl;
import com.gantzgulch.lego.platform.ev3.device.EV3TouchSensorImpl;
import com.gantzgulch.lego.util.Cast;

public class EV3DeviceDescriptorMap<T extends Device<?>, U extends Device<?>> {

    public static class OutputDeviceDescriptorMap extends EV3DeviceDescriptorMap<OutputDevice<?>, OutputDevice<?>> {

        public OutputDeviceDescriptorMap() {
            add(EV3LargeMotor.class, EV3LargeMotorImpl.class, "tacho-motor", "lego-ev3-l-motor");
            add(EV3MediumMotor.class, EV3MediumMotorImpl.class, "tacho-motor", "lego-ev3-m-motor");
        }
    }

    public static class InputDeviceDescriptorMap extends EV3DeviceDescriptorMap<InputDevice<?>, InputDevice<?>> {

        public InputDeviceDescriptorMap() {
            add(EV3ColorSensor.class, EV3ColorSensorImpl.class, "lego-sensor", "lego-ev3-color");
            add(EV3TouchSensor.class, EV3TouchSensorImpl.class, "lego-sensor", "lego-ev3-touch");
        }
    }

    private Map<Class<? extends T>, DeviceDescriptor<? extends T, ? extends U>> map = new HashMap<>();

    public EV3DeviceDescriptorMap() {

    }

    public void add(final Class<? extends T> deviceClass, final Class<? extends U> implClass, final String sysFsClass, final String driverName) {

        final DeviceDescriptor<? extends T, ? extends U> dd = new DeviceDescriptor<>(deviceClass, implClass, sysFsClass, driverName);

        map.put(dd.getDeviceClass(), dd);

    }

    public <D extends T> Optional<DeviceDescriptor<D, ? extends U>> find(final Class<D> deviceClass) {

        final DeviceDescriptor<D, ? extends U> dd = Cast.cast(map.get(deviceClass));

        return Optional.ofNullable(dd);

    }

    public static class DeviceDescriptor<I extends Device<?>, D extends Device<?>> {

        private static final EV3Logger LOG = EV3Logger.getLogger(DeviceDescriptor.class);

        private final Class<I> deviceClass;

        private final Class<D> implClass;

        private final String sysFsClass;

        private final String driverName;

        public DeviceDescriptor(//
                final Class<I> deviceClass, //
                final Class<D> implClass, //
                final String sysFsClass, //
                final String driverName) {

            this.deviceClass = deviceClass;
            this.implClass = implClass;
            this.sysFsClass = sysFsClass;
            this.driverName = driverName;
        }

        public Class<I> getDeviceClass() {
            return deviceClass;
        }

        public Class<D> getImplClass() {
            return implClass;
        }

        public String getSysFsClass() {
            return sysFsClass;
        }

        public String getDriverName() {
            return driverName;
        }

        public I createDevice(final Path path) {

            try {

                final Constructor<? extends Device<?>> ctor = implClass.getConstructor(Path.class);

                final Object o = ctor.newInstance(path);

                return deviceClass.cast(o);

            } catch (final ReflectiveOperationException | SecurityException | IllegalArgumentException e) {

                LOG.warning(e, "Unable to create device: %s", e.getMessage());

                throw new RuntimeException(e);
            }

        }
    }
}
