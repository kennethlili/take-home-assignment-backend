package com.spatiallaser.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception for handling bad request scenarios in API calls. This
 * exception is thrown when a request cannot be processed due to client error.
 *
 * The exception is mapped to HTTP 400 (BAD_REQUEST) status code when thrown in
 * controller methods.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
