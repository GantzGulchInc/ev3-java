package com.gantzgulch.lego.shell;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.gantzgulch.lego.shell.command.HelpCommand;
import com.gantzgulch.lego.shell.command.NullCommand;
import com.gantzgulch.lego.shell.command.OnForRotationsCommand;
import com.gantzgulch.lego.shell.command.QuitCommand;

public class CommandParser {

    private Map<String,Function<String[],Command>> commandParserMap = new HashMap<>();
    
    public CommandParser() {
        
        commandParserMap.put("help", HelpCommand::parse);
        commandParserMap.put("?", HelpCommand::parse);
        commandParserMap.put("onForRotations", OnForRotationsCommand::parse);
        commandParserMap.put("quit", QuitCommand::parse);
    }

    public Command parse(final String commandString) {
        
        final String[] args = split(commandString);
    
        if( args.length == 0 ) {
            return NullCommand.parse( new String[0] );
        }
        
        final Function<String[],Command> func = commandParserMap.get(args[0]);
        
        if( func == null ) {
            throw new IllegalArgumentException("Unknown command: " + args[0] );
        }
        
        return func.apply(args);
    }
    
    
    public String[] split(final String commandString) {
        
        if( commandString == null ) {
            return new String[0];
        }

        return commandString.strip().split("\\s+");
    }
    
}
