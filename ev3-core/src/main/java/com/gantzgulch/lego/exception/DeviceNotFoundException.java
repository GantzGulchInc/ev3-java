package com.gantzgulch.lego.exception;

public class DeviceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -3078915490669869585L;

    public DeviceNotFoundException() {
    }

    public DeviceNotFoundException(String message) {
        super(message);
    }

    public DeviceNotFoundException(Throwable cause) {
        super(cause);
    }

    public DeviceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeviceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
