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
package com.gantzgulch.lego.ev3dev.attribute;

import java.nio.file.Path;

public final class AttributeFactory {

    private AttributeFactory() {

    }

    public static Attribute createAttribute(final AttributeType type, boolean preWarm, final Path sysPath) {
        return new AttributeFileChannel(type, preWarm, sysPath);
    }

    public static Attribute createAttribute(final AttributeType type, boolean preWarm, final Path sysPath, final String... attributeName) {
        return new AttributeFileChannel(type, preWarm, sysPath, attributeName);
    }

}
