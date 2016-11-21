package com.duck.game.unimplemented;

/**
 * @author Kieran Hall KJH532
 * @version 1.0
 * @since 1.0
 */
public class InvalidStringArgumentException extends Exception {
    /**
     * Hold the erroneous argument.
     */
    private String arg;

    /**
     * Constructor of the exception
     *
     * @param arg Variable holding the argument throwing the exception
     */
    public InvalidStringArgumentException(String arg) {
        this.arg = arg;
    }

    /**
     * getter for the arg variable
     *
     * @return arg the argument variable
     */
    public String getArg() {
        return arg;
    }
}
