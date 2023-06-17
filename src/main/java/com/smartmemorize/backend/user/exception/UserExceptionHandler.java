package com.smartmemorize.backend.user.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

  private final Logger logger = LoggerFactory.getLogger(UserExceptionHandler.class);

  @ExceptionHandler(UserExistsException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ProblemDetail handleUserExistsException(UserExistsException e) {
    logger.error(e.getMessage());

    ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
    pd.setTitle("User already exists");

    return pd;
  }

  @ExceptionHandler(UserNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ProblemDetail handleUserNotFoundException(UserNotFoundException e) {
    logger.error(e.getMessage());

    ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    pd.setTitle("User not found");

    return pd;
  }
}
