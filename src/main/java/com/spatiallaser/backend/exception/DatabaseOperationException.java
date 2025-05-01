package com.spatiallaser.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception for handling database operation errors in the application.
 * This exception is thrown when there is an issue with database operations,
 * such as connection failures or query execution errors.
 *
 * The exception is mapped to HTTP 500 (INTERNAL_SERVER_ERROR) status code when
 * thrown in controller methods.
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DatabaseOperationException extends RuntimeException {

    public DatabaseOperationException(String message) {
        super(message);
    }

    public DatabaseOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
