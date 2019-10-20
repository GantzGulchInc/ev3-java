package com.gantzgulch.ev3.platform.ev3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.gantzgulch.ev3.logging.EV3Logger;
import com.gantzgulch.ev3.util.FileOperator;

public class DeviceFinder {

    public static final Path SYS_CLASS = Path.of("/sys/class");

    private static final EV3Logger LOG = EV3Logger.getLogger(DeviceFinder.class);

    public DeviceFinder() {

    }

    public Optional<Path> findDevicePath(final String deviceClass, final String deviceDriverName, final String deviceAddress) {

        LOG.finer("findDevicePath: deviceClass: %s, deviceDriverName: %s, deviceAddress: %s", deviceClass, deviceDriverName, deviceAddress);

        final Path deviceClassPath = SYS_CLASS.resolve(deviceClass);

        List<Path> found = new ArrayList<>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(deviceClassPath)) {

            stream.forEach(p -> {

                try {

                    LOG.finest("findDevicePath: checking: %s", p);
                    
                    final String foundDeviceDriverName = readDeviceDriverName(p);

                    LOG.finest("findDevicePath: driverName: %s", foundDeviceDriverName);
                    
                    final String foundDeviceAddress = readDeviceAddress(p);

                    LOG.finest("findDevicePath: deviceAddress: %s", foundDeviceAddress);
                    
                    if (Objects.equals(deviceDriverName, foundDeviceDriverName) && Objects.equals(deviceAddress, foundDeviceAddress)) {
                        
                        LOG.finer("findDevicePath: found matchinge device...");
                        
                        found.add(p);
                    }

                } catch (final IOException | RuntimeException e) {
                    LOG.warning(e, "findDevicePath: Skipping path: %s", p);
                }

            });

            if (found.size() > 1) {
                throw new RuntimeException("Multiple devices found: " + found.size());
            }

        } catch (final IOException e) {

            LOG.warning(e, "Error looking for device path: %s", e.getMessage());

        }

        return found.size() > 0 ? Optional.of(found.get(0)) : Optional.empty();
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
