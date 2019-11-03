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
package com.gantzgulch.lego.util.lang;

import java.io.IOException;
import java.io.StringReader;
import java.util.Optional;
import java.util.Properties;

import com.gantzgulch.lego.util.logger.EV3Logger;

public final class StringUtil {

    private static final EV3Logger LOG = EV3Logger.getLogger(StringUtil.class);
    
    private StringUtil() {
    }

    public static Optional<Integer> parseInteger(final String value) {
        
        try {
            
            return Optional.of(Integer.parseInt(value.strip()));
            
        }catch(final NullPointerException | NumberFormatException e) {
            
            LOG.warning(e, "parseInteger: error parse '%s'", value);
            
            return Optional.empty();
        }
    }

    public static Optional<Long> parseLong(final String value) {
        
        try {
            
            return Optional.of(Long.parseLong(value.strip()));
            
        }catch(final NullPointerException | NumberFormatException e) {
            
            LOG.warning(e, "parseLong: error parse '%s'", value);
            
            return Optional.empty();
        }
    }
    
    public static Properties parseProperties(final String value) {

        final Properties props = new Properties();

        try {

            props.load(new StringReader(value));

        } catch (final IOException e) {
            LOG.warning(e, "Error parsing board properties.");
        }

        return props;
    }
}
