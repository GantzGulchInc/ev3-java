package com.gantzgulch.lego.exception;

public class DeviceException extends RuntimeException {

	private static final long serialVersionUID = 837775100432651704L;

	public DeviceException() {
	}

	public DeviceException(final String message) {
		super(message);
	}

	public DeviceException(final Throwable cause) {
		super(cause);
	}

	public DeviceException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public DeviceException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
