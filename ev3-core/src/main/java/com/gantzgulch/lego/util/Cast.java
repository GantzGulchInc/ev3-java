package com.gantzgulch.lego.util;

public final class Cast {

	private Cast() {
	}

	
	@SuppressWarnings("unchecked")
	public static <D> D cast(final Object o) {
		return (D)o;
	}
}
