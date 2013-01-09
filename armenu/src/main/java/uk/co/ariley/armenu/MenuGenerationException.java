/*
 * 
 */
package uk.co.ariley.armenu;

/**
 *
 * @author ariley
 */
public class MenuGenerationException extends Exception {

    /**
     * Creates a new instance of
     * <code>MenuGenerationException</code> without detail message.
     */
    public MenuGenerationException() {
    }


    /**
     * Constructs an instance of
     * <code>MenuGenerationException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public MenuGenerationException(String msg) {
        super(msg);
    }


    public MenuGenerationException(Throwable cause) {
        super(cause);
    }


    public MenuGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}
