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

public class Length {

    private final double millimeters;
    
    public Length(final double millimeters) {
        this.millimeters = millimeters;
        
    }
    
    public double toMillimeters() {
        return millimeters;
    }
    
    public static Length ofMillimeters(final double millimeters) {
        return new Length(millimeters);
    }

    public static Length ofCentimeters(final double centimeters) {
        return new Length(centimeters * 10);
    }

    public static Length ofDecimeters(final double decimeters) {
        return new Length(decimeters * 100);
    }

    public static Length ofMeters(final double meters) {
        return new Length(meters * 1000);
    }

    public static Length ofInches(final double inches) {
        return new Length(inches * 25.4);
    }

    public static Length ofFeet(final double feet) {
        return new Length(feet * 304.8);
    }

}
