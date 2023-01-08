package com.pic.velib.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UserWrongPasswordHTTPException extends RuntimeException {
    public UserWrongPasswordHTTPException() {
        super();
    }
    public UserWrongPasswordHTTPException(String message, Throwable cause) {
        super(message, cause);
    }
    public UserWrongPasswordHTTPException(String message) {
        super(message);
    }
    public UserWrongPasswordHTTPException(Throwable cause) {
        super(cause);
    }
}
