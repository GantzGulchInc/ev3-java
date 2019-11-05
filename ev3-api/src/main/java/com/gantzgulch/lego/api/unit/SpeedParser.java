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
package com.gantzgulch.lego.api.unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class SpeedParser {

    private static final String REGEX_DOUBLE = "(\\-?\\d+(?:\\.\\d+)?)";

    private static final Pattern PATTERN_PERCENT = Pattern.compile(REGEX_DOUBLE + "%");
    private static final Pattern PATTERN_RPM = Pattern.compile(REGEX_DOUBLE + "rpm");
    private static final Pattern PATTERN_RPS = Pattern.compile(REGEX_DOUBLE + "rps");
    private static final Pattern PATTERN_DPM = Pattern.compile(REGEX_DOUBLE + "dpm");
    private static final Pattern PATTERN_DPS = Pattern.compile(REGEX_DOUBLE + "dps");
    private static final Pattern PATTERN_NATIVE = Pattern.compile(REGEX_DOUBLE + "n");

    private static final List<PatternParser<Double, Speed>> SPEED_PARSERS = createSpeedParser();

    public static Speed parse(final String value) {

        if (value == null) {
            throw new NullPointerException();
        }

        for (final PatternParser<Double, Speed> p : SPEED_PARSERS) {

            Optional<Speed> s = p.accept(value);

            if (s.isPresent()) {
                return s.get();
            }
        }

        throw new IllegalArgumentException("Unable to parse speed: " + value);

    }

    private static List<PatternParser<Double, Speed>> createSpeedParser() {

        final List<PatternParser<Double, Speed>> l = new ArrayList<>();

        l.add(new PatternParser<Double, Speed>(PATTERN_PERCENT, Double::parseDouble, SpeedPercent::new));
        l.add(new PatternParser<Double, Speed>(PATTERN_RPM, Double::parseDouble, SpeedRPM::new));
        l.add(new PatternParser<Double, Speed>(PATTERN_RPS, Double::parseDouble, SpeedRPS::new));
        l.add(new PatternParser<Double, Speed>(PATTERN_DPM, Double::parseDouble, SpeedDPM::new));
        l.add(new PatternParser<Double, Speed>(PATTERN_DPS, Double::parseDouble, SpeedDPS::new));
        l.add(new PatternParser<Double, Speed>(PATTERN_NATIVE, Double::parseDouble, SpeedNative::new));

        return l;
    }

}
