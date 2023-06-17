package com.smartmemorize.backend.card;

import com.smartmemorize.backend.card.dto.CardResponseDTO;
import com.smartmemorize.backend.card.dto.CreateCardDTO;
import com.smartmemorize.backend.user.CurrentUser;
import com.smartmemorize.backend.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/decks/{deckId}/cards")
@Tag(name = "Cards")
public class CardController {

  private final CardService cardService;

  public CardController(CardService cardService) {
    this.cardService = cardService;
  }

  @PostMapping
  @Operation(summary = "Create a new card", description = "Create a new card within the given deck")
  public ResponseEntity<CardResponseDTO> createCard(@CurrentUser User user,
      @PathVariable Long deckId,
      @Valid @RequestBody CreateCardDTO createCardDTO) {
    CardResponseDTO responseDTO = cardService.createCard(user, deckId, createCardDTO);
    return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
  }
}
