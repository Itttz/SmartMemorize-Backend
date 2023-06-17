package com.smartmemorize.backend.deck;

import com.smartmemorize.backend.deck.dto.CreateDeckDTO;
import com.smartmemorize.backend.deck.dto.DeckResponseDTO;
import com.smartmemorize.backend.user.CurrentUser;
import com.smartmemorize.backend.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/decks")
@Tag(name = "Decks")
public class DeckController {

  private final DeckService deckService;

  public DeckController(DeckService deckService) {
    this.deckService = deckService;
  }

  @PostMapping
  @Operation(summary = "Create a new deck", description = "Create a new deck for the current user")
  public ResponseEntity<DeckResponseDTO> createDeck(@CurrentUser User user,
      @Valid @RequestBody CreateDeckDTO createDeckDTO) {
    DeckResponseDTO responseDTO = deckService.createDeck(user, createDeckDTO);
    return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
  }

  @GetMapping
  @Operation(summary = "Get all decks", description = "Get all decks for the current user")
  public ResponseEntity<List<DeckResponseDTO>> getAllDecks(@CurrentUser User user) {
    List<DeckResponseDTO> decks = deckService.getAllDecks(user);
    return ResponseEntity.ok(decks);
  }

  @PostMapping("/{deckId}/invite")
  @Operation(summary = "Invite a user to a deck", description = "Invite a user to a deck")
  public ResponseEntity<Void> inviteUserToDeck(@CurrentUser User user, @PathVariable Long deckId,
      @RequestParam Long userId) {
    deckService.inviteUserToDeck(user, deckId, userId);
    return ResponseEntity.ok().build();
  }
}
