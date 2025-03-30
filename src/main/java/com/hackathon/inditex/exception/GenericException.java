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

    /**
     * Constructs a new instance of this class with the specified message.
     *
     * @param message the message describing the exception
     */
    public GenericException(String message) {
        super(message);
    }
}
