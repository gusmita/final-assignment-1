package edu.kit.informatik.train;

/**
 * Encapsulates an exception which is thrown in case of invalid user input
 * 
 * @author Gusmita
 * @version 1.0
 */
public class InputException extends Exception {
    
    /**
     * The conctructor of the InputException. Thrown an error message.
     * @param message to display to user
     */
    public InputException(String message) {
        super(message);
    }

}
