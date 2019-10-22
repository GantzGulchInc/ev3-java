package com.gantzgulch.lego.platform.ev3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.gantzgulch.lego.exception.DeviceException;
import com.gantzgulch.lego.logging.EV3Logger;
import com.gantzgulch.lego.util.FileOperator;

public class DeviceFinder {

    public static final Path SYS_CLASS = Path.of("/sys/class");

    private static final EV3Logger LOG = EV3Logger.getLogger(DeviceFinder.class);

    public DeviceFinder() {

    }

    public Optional<Path> findDevicePath(final String deviceClass, final String deviceDriverName, final String deviceAddress) {

        LOG.finer("findDevicePath: deviceClass: %s, deviceDriverName: %s, deviceAddress: %s", deviceClass, deviceDriverName, deviceAddress);

        final Path deviceClassPath = SYS_CLASS.resolve(deviceClass);

        List<Path> found = new ArrayList<>();

        final DeviceFilter deviceFilter = new DeviceFilter(deviceDriverName, deviceAddress);
        
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(deviceClassPath, deviceFilter)) {

            stream.forEach(found::add);

            if (found.size() > 1) {
                throw new DeviceException("Error finding device, multiple devices found: " + found.size());
            }

        } catch (final IOException e) {

            LOG.warning(e, "Error looking for device path: %s", e.getMessage());
            
            throw new DeviceException("Error finding device.", e);

        }

        return found.size() > 0 ? Optional.of(found.get(0)) : Optional.empty();
    }

    public static final class DeviceFilter implements DirectoryStream.Filter<Path> {

        private static final EV3Logger LOG = EV3Logger.getLogger(DeviceFilter.class);

        private final String deviceDriverName;
        private final String deviceAddress;

        public DeviceFilter(final String deviceDriverName, final String deviceAddress) {
            this.deviceDriverName = deviceDriverName;
            this.deviceAddress = deviceAddress;
        }

        @Override
        public boolean accept(final Path p) throws IOException {

            try {

                LOG.finest("findDevicePath: checking: %s", p);

                final String foundDeviceDriverName = readDeviceDriverName(p);

                LOG.finest("findDevicePath: driverName: %s", foundDeviceDriverName);

                final String foundDeviceAddress = readDeviceAddress(p);

                LOG.finest("findDevicePath: deviceAddress: %s", foundDeviceAddress);

                return Objects.equals(deviceDriverName, foundDeviceDriverName) && Objects.equals(deviceAddress, foundDeviceAddress);

            } catch (final IOException | RuntimeException e) {
                LOG.warning(e, "findDevicePath: Skipping path: %s", p);
                return false;
            }

        }

        private String readDeviceDriverName(final Path path) throws IOException {

            final Path driverNamePath = path.resolve("driver_name");

            return FileOperator.readStringStripTrailing(driverNamePath, 100);
        }

        private String readDeviceAddress(final Path path) throws IOException {

            final Path addressPath = path.resolve("address");

            return FileOperator.readStringStripTrailing(addressPath, 100);
        }

    }
}
