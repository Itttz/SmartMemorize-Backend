package com.smartmemorize.backend.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserExistsException extends RuntimeException {
    public UserExistsException(String name) {
        super("User with name " + name + " already exists");
    }
}
