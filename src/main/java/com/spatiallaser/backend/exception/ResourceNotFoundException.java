/**
 * Custom exception for handling resource not found scenarios in API calls.
 * This exception is thrown when a requested resource cannot be found in the system.
 *
 * The exception is mapped to HTTP 404 (NOT_FOUND) status code when thrown in controller methods.
 */
package com.spatiallaser.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
    }
}
