package com.gantzgulch.lego.util.exception;

public class PortNotFoundException extends PlatformRuntimeException {

    private static final long serialVersionUID = -3078915490669869585L;

    public PortNotFoundException() {
    }

    public PortNotFoundException(String message) {
        super(message);
    }

    public PortNotFoundException(Throwable cause) {
        super(cause);
    }

    public PortNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PortNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
