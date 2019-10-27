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
