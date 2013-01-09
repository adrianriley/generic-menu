/*
 * 
 */
package uk.co.ariley.armenu.command;

/**
 *
 * @author ariley
 */
public class CommandException extends Exception {

    /**
     * Creates a new instance of
     * <code>CommandException</code> without detail message.
     */
    public CommandException() {
    }


    /**
     * Constructs an instance of
     * <code>CommandException</code> with the specified detail message.
     *
     * @param message the detail message.
     */
    public CommandException(String message) {
        super(message);
    }


    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }


    public CommandException(Throwable cause) {
        super(cause);
    }
}
