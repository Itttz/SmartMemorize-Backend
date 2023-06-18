package com.smartmemorize.backend.deck.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyInDeckException extends RuntimeException {
  public UserAlreadyInDeckException() {
    super("User is already in Deck");
  }
}
