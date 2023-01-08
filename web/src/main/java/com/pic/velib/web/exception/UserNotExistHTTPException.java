package com.pic.velib.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotExistHTTPException extends RuntimeException {
    public UserNotExistHTTPException() {
        super();
    }
    public UserNotExistHTTPException(String message, Throwable cause) {
        super(message, cause);
    }
    public UserNotExistHTTPException(String message) {
        super(message);
    }
    public UserNotExistHTTPException(Throwable cause) {
        super(cause);
    }
}
