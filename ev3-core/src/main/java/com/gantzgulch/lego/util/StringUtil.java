package com.gantzgulch.lego.util;

import java.util.Optional;

import com.gantzgulch.lego.logging.EV3Logger;

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
}
