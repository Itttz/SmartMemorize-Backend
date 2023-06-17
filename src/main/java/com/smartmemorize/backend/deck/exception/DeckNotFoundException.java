package com.smartmemorize.backend.deck.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DeckNotFoundException extends RuntimeException {

  public DeckNotFoundException(Long id) {
    super("Deck with id " + id + " does not exist");
  }
}
