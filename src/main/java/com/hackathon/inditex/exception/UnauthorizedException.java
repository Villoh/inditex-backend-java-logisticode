package com.hackathon.inditex.exception;

/**
 * This class represents an exception that occurs when a user is not authorized to perform an action.
 */
public class UnauthorizedException extends GenericException {

    /**
     * Constructs a new instance of this class with the specified detail message.
     *
     * @param message the detail message
     */
    public UnauthorizedException(boolean success, String message) {
        super(success, message);
    }

    /**
     * Constructs a new instance of this class with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause   the cause
     */
    public UnauthorizedException(boolean success, String message, Throwable cause) {
        super(success, message, cause);
    }
}
