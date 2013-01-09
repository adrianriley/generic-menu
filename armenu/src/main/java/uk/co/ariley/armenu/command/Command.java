/*
 * 
 */
package uk.co.ariley.armenu.command;

/**
 *
 * @author ariley
 */
public interface Command {
    public void execute() throws CommandException;


    void addArgument(final String argument);
}
