/*******************************************************************************
 *    Copyright 2019 GantzGulch, Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *******************************************************************************/
package com.gantzgulch.lego.common.logger;

import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

public class CustomFormatter extends SimpleFormatter {

	public CustomFormatter() {

	}
	
	@Override
	public String format(final LogRecord record) {

	    String shortClassName = record.getSourceClassName();
	    
	    if( shortClassName == null ) {
	        shortClassName = record.getLoggerName();
	    }
	    
		record.setSourceClassName( trim(shortClassName) );
		
		return super.format(record);
	}
	
	private String trim(final String value) {
		
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
