package com.smartmemorize.backend.deck;

import com.smartmemorize.backend.deck.dto.CreateDeckDTO;
import com.smartmemorize.backend.deck.dto.DeckResponseDTO;
import com.smartmemorize.backend.security.UserPrinciple;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<DeckResponseDTO> createDeck(@AuthenticationPrincipal UserPrinciple principle,
                                                      @Valid @RequestBody CreateDeckDTO createDeckDTO) {
        DeckResponseDTO responseDTO = deckService.createDeck(principle.getUser(), createDeckDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all decks", description = "Get all decks for the current user")
    public ResponseEntity<List<DeckResponseDTO>> getAllDecks(@AuthenticationPrincipal UserPrinciple principle) {
        List<DeckResponseDTO> decks = deckService.getAllDecks(principle.getUser());
        return ResponseEntity.ok(decks);
    }
}
