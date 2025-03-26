package com.hackathon.inditex.exception;

import lombok.Getter;

/**
 * This class is designed to be used as a base class for other exception classes in the application,
 * providing a consistent structure and behavior for handling exceptions.
 * <p>
 * It extends the {@link RuntimeException} class and includes fields for a boolean success flag,
 * a message string, and an optional cause throwable.
 */
@Getter
public class GenericException extends RuntimeException {
    private final boolean success;
    private final String message;
    private Throwable cause;

    /**
     * Constructs a new instance of this class with the specified success flag and message.
     *
     * @param success  the success status of the operation
     * @param message the message describing the exception
     */
    public GenericException(boolean success, String message) {
        super(message);
        this.success = success;
        this.message = message;
    }

    /**
     * Constructs a new instance of this class with the specified success flag, message, and cause.
     *
     * @param success  the success status of the operation
     * @param message the message describing the exception
     * @param cause   the throwable that caused this exception
     */
    public GenericException(boolean success, String message, Throwable cause) {
        super(message);
        this.success = success;
        this.message = message;
        this.cause = cause;
    }
}
