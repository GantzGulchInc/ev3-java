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
package com.gantzgulch.lego.cli;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.gantzgulch.lego.shell.CommandParser;
import com.gantzgulch.lego.shell.command.NullCommand;

public class CommandParserTest {

    public CommandParserTest() {
        
    }

    @Test
    public void testCommandStringSplitter() {
        
        final CommandParser parser = new CommandParser();
        
        assertThat(parser.split("onForRotations"), Matchers.equalTo( new String[]{ "onForRotations" } ) );
        
        assertThat(parser.split("onForRotations 2"), Matchers.equalTo( new String[]{ "onForRotations", "2" } ) );
        
        assertThat(parser.split("onForRotations   2"), Matchers.equalTo( new String[]{ "onForRotations", "2" } ) );
        
        assertThat(parser.split("   onForRotations   2 \t 3 "), Matchers.equalTo( new String[]{ "onForRotations", "2", "3" } ) );
    }


    @Test
    public void testParseNullCommand() {
        
        final CommandParser parser = new CommandParser();
        
        assertThat(parser.parse(null), Matchers.isA(NullCommand.class) );

        assertThat(parser.parse(""), Matchers.isA(NullCommand.class) );
        
        assertThat(parser.parse("     "), Matchers.isA(NullCommand.class) );

        assertThat(parser.parse("\t\t\t\t\t"), Matchers.isA(NullCommand.class) );
    }


}
