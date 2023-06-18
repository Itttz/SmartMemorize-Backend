package com.smartmemorize.backend.deck.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DeckExceptionHandler {

  private final Logger logger = LoggerFactory.getLogger(DeckExceptionHandler.class);

  @ExceptionHandler(DeckNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ProblemDetail handleDeckNotFoundException(DeckNotFoundException e) {
    logger.error(e.getMessage());

    ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    pd.setTitle("Deck not found");

    return pd;
  }

  @ExceptionHandler(UserAlreadyInDeckException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ProblemDetail handleUserAlreadyInDeckException(UserAlreadyInDeckException e) {
    logger.error(e.getMessage());

    ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
    pd.setTitle("User already in deck");

    return pd;
  }
}
