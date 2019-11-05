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
import java.util.regex.Pattern;

public class LengthParser {

    private static final String REGEX_DOUBLE = "(\\-?\\d+(?:\\.\\d+)?)";

    private static final Pattern PATTERN_MILLIMETERS = Pattern.compile(REGEX_DOUBLE + "mm");
    private static final Pattern PATTERN_CENTIMETERS = Pattern.compile(REGEX_DOUBLE + "cm");
    private static final Pattern PATTERN_METERS = Pattern.compile(REGEX_DOUBLE + "m");
    private static final Pattern PATTERN_INCHES = Pattern.compile(REGEX_DOUBLE + "in");
    private static final Pattern PATTERN_FEET = Pattern.compile(REGEX_DOUBLE + "ft");

    private static final List<PatternParser<Double, Length>> LENGTH_PARSERS = createLengthParser();

    public static Length parse(final String value) {

        return PatternParser//
                .parse(value, LENGTH_PARSERS)//
                .orElseThrow(() -> {
                    return new IllegalArgumentException("Unable to parse length: " + value);
                });
        
    }

    private static List<PatternParser<Double, Length>> createLengthParser() {

        final List<PatternParser<Double, Length>> l = new ArrayList<>();

        l.add(new PatternParser<Double, Length>(PATTERN_MILLIMETERS, Double::parseDouble, Length::ofMillimeters));
        l.add(new PatternParser<Double, Length>(PATTERN_CENTIMETERS, Double::parseDouble, Length::ofCentimeters));
        l.add(new PatternParser<Double, Length>(PATTERN_METERS, Double::parseDouble, Length::ofMeters));
        l.add(new PatternParser<Double, Length>(PATTERN_INCHES, Double::parseDouble, Length::ofInches));
        l.add(new PatternParser<Double, Length>(PATTERN_FEET, Double::parseDouble, Length::ofFeet));

        return l;
    }

}
