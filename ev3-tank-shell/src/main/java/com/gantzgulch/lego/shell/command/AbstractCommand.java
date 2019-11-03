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
package com.gantzgulch.lego.shell.command;

import com.gantzgulch.lego.shell.Command;
import com.gantzgulch.lego.util.logger.EV3Logger;

public abstract class AbstractCommand implements Command {

    protected EV3Logger LOG = EV3Logger.getLogger(getClass());

    private final boolean isRecordable;

    protected String[] args;

    public AbstractCommand(final boolean isRecordable, final String[] args) {
        this.isRecordable = isRecordable;
        this.args = args;
    }

    @Override
    public boolean isRecordable() {
        return isRecordable;
    }
}
