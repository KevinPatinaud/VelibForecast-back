package com.pic.velib.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_MODIFIED)
public class UserAlreadyExistHTTPException extends RuntimeException {
    public UserAlreadyExistHTTPException() {
        super();
    }
    public UserAlreadyExistHTTPException(String message, Throwable cause) {
        super(message, cause);
    }
    public UserAlreadyExistHTTPException(String message) {
        super(message);
    }
    public UserAlreadyExistHTTPException(Throwable cause) {
        super(cause);
    }
}
