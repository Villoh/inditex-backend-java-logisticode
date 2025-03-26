package com.hackathon.inditex.exception.handler;

import com.hackathon.inditex.exception.BadRequestException;
import com.hackathon.inditex.exception.InternalServerErrorException;
import com.hackathon.inditex.exception.NotFoundException;
import com.hackathon.inditex.exception.UnauthorizedException;
import com.hackathon.inditex.model.exception.CustomHttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for handling exceptions in the application.
 * This class handles specific types of exceptions and returns appropriate HTTP responses.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Handles UnauthorizedException and returns a 401 HTTP response.
     *
     * @param ex the UnauthorizedException to be handled
     * @return a CustomHttpResponse with success status set to false and error message
     */
    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public @ResponseBody CustomHttpResponse handleUnauthorizedException(UnauthorizedException ex) {
        return new CustomHttpResponse(ex.getMessage());
    }

    /**
     * Handles BadRequestException and returns a 400 HTTP response.
     *
     * @param ex the BadRequestException to be handled
     * @return a CustomHttpResponse with success status set to false and error message
     */
    @ExceptionHandler(value = BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody CustomHttpResponse handleBadRequestException(BadRequestException ex) {
        return new CustomHttpResponse(ex.getMessage());
    }

    /**
     * Handles NotFoundException and returns a 404 HTTP response.
     *
     * @param ex the NotFoundException to be handled
     * @return a CustomHttpResponse with success status set to false and error message
     */
    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody CustomHttpResponse handleBadRequestException(NotFoundException ex) {
        return new CustomHttpResponse(ex.getMessage());
    }

    /**
     * Handles InternalServerErrorException and returns a 500 HTTP response.
     *
     * @param ex the InternalServerErrorException to be handled
     * @return a CustomHttpResponse with success status set to false and error message
     */
    @ExceptionHandler(value = InternalServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody CustomHttpResponse handleInternalServerErrorException(InternalServerErrorException ex) {
    	return new CustomHttpResponse(ex.getMessage());
    }
}
