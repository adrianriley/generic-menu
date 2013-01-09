/*
 * 
 */
package uk.co.ariley.armenu.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ariley
 */
public class ExecuteCommand implements Command {
    
    private String command;
    
    private List<String> arguments;


    public String getCommand() {
        return command;
    }
    
    
    public void addArgument(final String argument) {
        if (command == null) {
            command = argument;
        } else {
        if (arguments == null) {
            arguments = new ArrayList<>();
        }
        arguments.add(argument);
        }
    }


    public List<String> getArguments() {
        if (arguments == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(arguments);
    }


    public void execute() throws CommandException {
        int count = 1 + getArguments().size();
        String[] cmdarray = new String[count];
        cmdarray[0] = command;
        int i = 0;
        for (String argument : getArguments()) {
            cmdarray[++i] = argument;
        }
        try {
            Runtime.getRuntime().exec(cmdarray);
        } catch (IOException ex) {
            Logger.getLogger(ExecuteCommand.class.getName()).log(Level.SEVERE, null, ex);
            throw new CommandException(command, ex);
        }
    }


    @Override
    public String toString() {
        return "ExecuteCommand{" + "command=" + command + ", arguments=" + arguments + '}';
    }
    
}
