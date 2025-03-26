package com.hackathon.inditex.exception;

import lombok.Getter;


/**
 * This class represents an exception indicating that a requested resource was not found.
 * It extends the GenericException class and provides additional functionality for handling exceptions related to resource not found errors.
 */
@Getter
public class NotFoundException extends GenericException {

    /**
     * Constructs a new instance of BadRequestException with the specified success status, message, and cause.
     *
     * @param success   the success status
     * @param message  the error message
     * @param cause     the cause of the exception (optional)
     */
    public NotFoundException(boolean success, String message, Throwable cause) {
        super(success, message, cause);
    }

    /**
     * Constructs a new instance of BadRequestException with the specified success status and message.
     *
     * @param success   the success status
     * @param message  the error message
     */
    public NotFoundException(boolean success, String message) {
        super(success, message);
    }
}