package com.hackathon.inditex.exception;

import lombok.Getter;


/**
 * Represents an internal server error exception.
 * This exception is used to indicate that an unexpected condition occurred on the server side,
 * preventing it from fulfilling a request. It typically includes a detailed error message and may be accompanied by additional context or debugging information.
 */
@Getter
public class InternalServerErrorException extends GenericException {

    /**
     * Constructs a new instance of InternalServerErrorException with the specified success status, message, and cause.
     *
     * @param success   the success status
     * @param message  the error message
     * @param cause     the cause of the exception (optional)
     */
    public InternalServerErrorException(boolean success, String message, Throwable cause) {
        super(success, message, cause);
    }

    /**
     * Constructs a new instance of InternalServerErrorException with the specified success status and message.
     *
     * @param success   the success status
     * @param message  the error message
     */
    public InternalServerErrorException(boolean success, String message) {
        super(success, message);
    }
}