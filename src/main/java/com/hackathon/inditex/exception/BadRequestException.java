package com.hackathon.inditex.exception;

import lombok.Getter;


/**
 * An exception indicating that a request has been made with invalid or malformed data.
 */
@Getter
public class BadRequestException extends GenericException {

    /**
     * Constructs a new instance of BadRequestException with the specified success status, message, and cause.
     *
     * @param success   the success status
     * @param message  the error message
     * @param cause     the cause of the exception (optional)
     */
    public BadRequestException(boolean success, String message, Throwable cause) {
        super(success, message, cause);
    }

    /**
     * Constructs a new instance of BadRequestException with the specified success status and message.
     *
     * @param success   the success status
     * @param message  the error message
     */
    public BadRequestException(boolean success, String message) {
        super(success, message);
    }
}