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
package com.gantzgulch.lego.util.logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class EV3Logger {

    @SuppressWarnings("unused")
    private static final boolean isLoaded = loadConfiguration();

    private static final Map<Class<?>, EV3Logger> LOG_CACHE = new HashMap<>();

    public synchronized static EV3Logger getLogger(final Class<?> logClass) {

        EV3Logger logger = LOG_CACHE.get(logClass);

        if (logger == null) {
            logger = new EV3Logger(logClass);
            LOG_CACHE.put(logClass, logger);
        }

        return logger;
    }

    private final Logger log;

    private EV3Logger(final Class<?> logClass) {
        log = Logger.getLogger(logClass.getName());
    }

    protected void log(final Level level, final String msg) {
        log.logp(level, log.getName(), null, msg);
    }

    protected void log(final Level level, final Throwable throwable, final String msg) {
        log.logp(level, log.getName(), null, msg, throwable);
    }

    protected void log(final Level level, final String msg, final Object... args) {
        if (log.isLoggable(level)) {
            log.logp(level, log.getName(), null, format(msg, args));
        }
    }

    protected void log(final Level level, final Throwable throwable, final String msg, final Object... args) {
        if (log.isLoggable(level)) {
            log.logp(level, log.getName(), null, format(msg, args), throwable);
        }
    }

    //
    // SEVERE
    //

    public void severe(final String msg) {
        log(Level.SEVERE, msg);
    }

    public void severe(final String msg, final Object... args) {
        log(Level.SEVERE, msg, args);
    }

    public void severe(final Throwable throwable, final String msg) {
        log(Level.SEVERE, throwable, msg);
    }

    public void severe(final Throwable throwable, final String msg, final Object... args) {
        log(Level.SEVERE, throwable, msg, args);
    }

    //
    // WARNING
    //

    public void warning(final String msg) {
        log(Level.WARNING, msg);
    }

    public void warning(final String msg, final Object... args) {
        log(Level.WARNING, msg, args);
    }

    public void warning(final Throwable throwable, final String msg) {
        log(Level.WARNING, throwable, msg);
    }

    public void warning(final Throwable throwable, final String msg, final Object... args) {
        log(Level.WARNING, throwable, msg, args);
    }

    //
    // INFO
    //

    public void info(final String msg) {
        log(Level.INFO, msg);
    }

    public void info(final String msg, final Object... args) {
        log(Level.INFO, msg, args);
    }

    public void info(final Throwable throwable, final String msg) {
        log(Level.INFO, throwable, msg);
    }

    public void info(final Throwable throwable, final String msg, final Object... args) {
        log(Level.INFO, throwable, msg, args);
    }

    //
    // CONFIG
    //

    public void config(final String msg) {
        log(Level.CONFIG, msg);
    }

    public void config(final String msg, final Object... args) {
        log(Level.CONFIG, msg, args);
    }

    public void config(final Throwable throwable, final String msg) {
        log(Level.CONFIG, throwable, msg);
    }

    public void config(final Throwable throwable, final String msg, final Object... args) {
        log(Level.CONFIG, throwable, msg, args);
    }

    //
    // FINE
    //

    public void fine(final String msg) {
        log(Level.FINE, msg);
    }

    public void fine(final String msg, final Object... args) {
        log(Level.FINE, msg, args);
    }

    public void fine(final Throwable throwable, final String msg) {
        log(Level.FINE, throwable, msg);
    }

    public void fine(final Throwable throwable, final String msg, final Object... args) {
        log(Level.FINE, throwable, msg, args);
    }

    //
    // FINER
    //

    public void finer(final String msg) {
        log(Level.FINER, msg);
    }

    public void finer(final String msg, final Object... args) {
        log(Level.FINER, msg, args);
    }

    public void finer(final Throwable throwable, final String msg) {
        log(Level.FINER, throwable, msg);
    }

    public void finer(final Throwable throwable, final String msg, final Object... args) {
        log(Level.FINER, throwable, msg, args);
    }

    //
    // FINEST
    //

    public void finest(final String msg) {
        log(Level.FINEST, msg);
    }

    public void finest(final String msg, final Object... args) {
        log(Level.FINEST, msg, args);
    }

    public void finest(final Throwable throwable, final String msg) {
        log(Level.FINEST, throwable, msg);
    }

    public void finest(final Throwable throwable, final String msg, final Object... args) {
        log(Level.FINEST, throwable, msg, args);
    }

    //
    // Methods
    //

    public void entering(final String sourceMethod) {
        log.entering(log.getName(), sourceMethod);
    }

    public void exiting(final String sourceMethod) {
        log.exiting(log.getName(), sourceMethod);
    }

    //
    // Private
    //

    private static String format(final String msg, final Object... args) {

        if (msg == null) {
            return "";
        }

        return String.format(msg, args);

    }

    private static boolean loadConfiguration() {

        if( loadConfiguration("/jul.properties") ) {
            return true;
        }
        
        if( loadConfiguration("/jul_default.properties")) {
            return true;
        }
        
        return false;
    }

    private static boolean loadConfiguration(final String name) {

        final LogManager lm = LogManager.getLogManager();

        try (final InputStream is = EV3Logger.class.getResourceAsStream(name)) {

            lm.readConfiguration(is);

            return true;

        } catch (final RuntimeException | IOException e) {
            return false;
        }

    }
}
