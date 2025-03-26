package com.hackathon.inditex.model.exception;

/**
 * Custom exception class for handling custom response status.
 * This exception is used to return custom HTTP status codes and messages.
 */
public record CustomHttpResponse(boolean success, String message) { }