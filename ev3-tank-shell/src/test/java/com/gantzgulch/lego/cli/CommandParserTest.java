package com.gantzgulch.lego.cli;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Ignore;
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
    @Ignore
    public void testParseNullCommand() {
        
        final CommandParser parser = new CommandParser();
        
        assertThat(parser.parse(""), Matchers.isA(NullCommand.class) );
        
        assertThat(parser.parse("     "), Matchers.isA(NullCommand.class) );

        assertThat(parser.parse("\t\t\t\t\t"), Matchers.isA(NullCommand.class) );
    }


}
