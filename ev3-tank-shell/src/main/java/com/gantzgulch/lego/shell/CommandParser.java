package com.gantzgulch.lego.shell;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.gantzgulch.lego.shell.command.HelpCommand;
import com.gantzgulch.lego.shell.command.NullCommand;
import com.gantzgulch.lego.shell.command.OnForDegreesCommand;
import com.gantzgulch.lego.shell.command.OnForDurationCommand;
import com.gantzgulch.lego.shell.command.OnForRotationsCommand;
import com.gantzgulch.lego.shell.command.QuitCommand;
import com.gantzgulch.lego.shell.command.RecorderCommand;
import com.gantzgulch.lego.shell.command.SetRampDownCommand;
import com.gantzgulch.lego.shell.command.SetRampUpCommand;
import com.gantzgulch.lego.shell.command.SteerForRotationsCommand;

public class CommandParser {

    private Map<String, Function<String[], Command>> commandParserMap = new HashMap<>();

    public CommandParser() {

        commandParserMap.put("help", HelpCommand::parse);
        commandParserMap.put("?", HelpCommand::parse);
        commandParserMap.put("onForRotations", OnForRotationsCommand::parse);
        commandParserMap.put("onForDegrees", OnForDegreesCommand::parse);
        commandParserMap.put("onForDuration", OnForDurationCommand::parse);
        commandParserMap.put("steerForRotations", SteerForRotationsCommand::parse);
        commandParserMap.put("setRampUp", SetRampUpCommand::parse);
        commandParserMap.put("setRampDown", SetRampDownCommand::parse);
        commandParserMap.put("recorder", RecorderCommand::parse);
        commandParserMap.put("quit", QuitCommand::parse);
    }

    public Command parse(final String commandString) {

        final String[] args = split(commandString);

        if (args.length == 0) {
            return NullCommand.parse(new String[0]);
        }

        if (args.length == 1 && args[0].length() == 0) {
            return NullCommand.parse(new String[0]);
        }

        final Function<String[], Command> func = commandParserMap.get(args[0]);

        if (func == null) {
            throw new IllegalArgumentException("Unknown command: " + args[0]);
        }

        return func.apply(args);
    }

    public String[] split(final String commandString) {

        if (commandString == null) {
            return new String[0];
        }

        return commandString.strip().split("\\s+");
    }

}
