package com.hackathon.inditex.exception;

import lombok.Getter;


/**
 * This class represents an exception indicating that a requested resource was not found.
 * It extends the GenericException class and provides additional functionality for handling exceptions related to resource not found errors.
 */
@Getter
public class NotFoundException extends GenericException {

    /**
     * Constructs a new instance of BadRequestException with the specified message.
     *
     * @param message  the error message
     */
    public NotFoundException(String message) {
        super(message);
    }
}