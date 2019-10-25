package com.gantzgulch.lego.platform.ev3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.gantzgulch.lego.exception.DeviceException;
import com.gantzgulch.lego.exception.DeviceNotFoundException;
import com.gantzgulch.lego.logging.EV3Logger;
import com.gantzgulch.lego.util.FileOperator;

public class DeviceFinder {

    public static final Path SYS_CLASS = Path.of("/sys/class");

    public static final Path SYS_CLASS_LEGO_PORT = SYS_CLASS.resolve("lego-port");
    
    private static final EV3Logger LOG = EV3Logger.getLogger(DeviceFinder.class);

    public DeviceFinder() {

    }

    public Optional<Path> findPortPath(final String portAddress) {

        LOG.finer("findPortPath: portAddress: %s", portAddress);

        final Path portClassPath = SYS_CLASS_LEGO_PORT;

        List<Path> found = new ArrayList<>();

        final DeviceAddressFilter filter = new DeviceAddressFilter(portAddress);
        
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(portClassPath, filter)) {

            stream.forEach(found::add);

            if (found.size() > 1) {
                throw new DeviceException("Error finding port, multiple ports found: " + found.size());
            }

        } catch (final IOException e) {

            LOG.warning(e, "Error looking for port: %s", e.getMessage());
            
            throw new DeviceException("Error finding port.", e);

        }

        return found.size() > 0 ? Optional.of(found.get(0)) : Optional.empty();
    }

    public Optional<Path> findDevicePath(final String deviceClass, final String deviceDriverName, final String deviceAddress) {

        LOG.finer("findDevicePath: deviceClass: %s, deviceDriverName: %s, deviceAddress: %s", deviceClass, deviceDriverName, deviceAddress);

        final Path deviceClassPath = SYS_CLASS.resolve(deviceClass);

        List<Path> found = new ArrayList<>();

        final DeviceDriverNameAndAddressFilter gilter = new DeviceDriverNameAndAddressFilter(deviceDriverName, deviceAddress);
        
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(deviceClassPath, gilter)) {

            stream.forEach(found::add);

            if (found.size() > 1) {
                throw new DeviceNotFoundException("Error finding device, multiple devices found: " + found.size());
            }

        } catch (final IOException e) {

            LOG.warning(e, "Error looking for device path: %s", e.getMessage());
            
            throw new DeviceNotFoundException("Unexpected exception finding device: " + e.getMessage(), e);

        }

        return found.size() > 0 ? Optional.of(found.get(0)) : Optional.empty();
    }

    public static abstract class AbstractFilter implements DirectoryStream.Filter<Path> {
        
        private final EV3Logger LOG = EV3Logger.getLogger(getClass());

        protected boolean hasDeviceDriverName(final Path path, final String driverName) throws IOException {

            final Path driverNamePath = path.resolve("driver_name");

            final String pathDeviceDriverName = FileOperator.readStringStripTrailing(driverNamePath, 100);
            
            LOG.finest("hasDeviceDriverName: looking for: '%s', found: '%s'", driverName, pathDeviceDriverName);
            
            return driverName.contentEquals(pathDeviceDriverName);
            
            
        }

        protected boolean hasDeviceAddress(final Path path, final String address) throws IOException {

            final Path addressPath = path.resolve("address");

            final String pathDeviceAddress = FileOperator.readStringStripTrailing(addressPath, 100);
            
            LOG.finest("hasDeviceAddress: looking for: '%s', found: '%s'", address, pathDeviceAddress);
            
            return address.contentEquals(pathDeviceAddress);
            
            
        }
        
    }
    
    public static final class DeviceDriverNameAndAddressFilter extends AbstractFilter {

        private final String deviceDriverName;
        private final String deviceAddress;

        public DeviceDriverNameAndAddressFilter(final String deviceDriverName, final String deviceAddress) {
            this.deviceDriverName = deviceDriverName;
            this.deviceAddress = deviceAddress;
        }

        @Override
        public boolean accept(final Path p) throws IOException {

            try {

                LOG.finest("accept: checking: %s", p);

                return hasDeviceDriverName(p, deviceDriverName) && hasDeviceAddress(p, deviceAddress);

            } catch (final IOException | RuntimeException e) {
                LOG.warning(e, "accept: Skipping path: %s", p);
                return false;
            }

        }

    }

    public static final class DeviceAddressFilter extends AbstractFilter {

        private final String deviceAddress;

        public DeviceAddressFilter(final String deviceAddress) {
            this.deviceAddress = deviceAddress;
        }

        @Override
        public boolean accept(final Path p) throws IOException {

            try {

                LOG.finest("accept: checking: %s", p);

                return hasDeviceAddress(p, deviceAddress);

            } catch (final IOException | RuntimeException e) {
                LOG.warning(e, "accept: Skipping path: %s", p);
                return false;
            }

        }

    }
}
