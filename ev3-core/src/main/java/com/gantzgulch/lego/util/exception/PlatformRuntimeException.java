package com.gantzgulch.lego.util.exception;

public class PlatformRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -7514965643202058361L;

    public PlatformRuntimeException() {
    }

    public PlatformRuntimeException(String message) {
        super(message);
    }

    public PlatformRuntimeException(Throwable cause) {
        super(cause);
    }

    public PlatformRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlatformRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
