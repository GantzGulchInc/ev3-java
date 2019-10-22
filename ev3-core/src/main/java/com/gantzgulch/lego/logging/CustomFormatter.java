package com.gantzgulch.lego.logging;

import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

public class CustomFormatter extends SimpleFormatter {

	public CustomFormatter() {
		
	}
	
	@Override
	public String format(final LogRecord record) {

		final String shortClassName = trim(record.getSourceClassName());
		
		record.setSourceClassName(shortClassName);
		
		return super.format(record);
	}
	
	private String trim(final String value) {
		
		// System.err.println("trim: value: '" + value + "'");
		
		if( value == null ) {
			return value;
		}
		
		int pos = value.lastIndexOf('.');
		
		if( pos < 0 ) {
			return value;
		}
		
		return value.substring(pos + 1);
	}
}
