package com.smartmemorize.backend.card;

import com.smartmemorize.backend.card.dto.CardResponseDTO;
import com.smartmemorize.backend.card.dto.CreateCardDTO;
import com.smartmemorize.backend.security.UserPrinciple;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<CardResponseDTO> createCard(@AuthenticationPrincipal UserPrinciple principle,
                                                      @PathVariable Long deckId,
                                                      @Valid @RequestBody CreateCardDTO createCardDTO) {
        CardResponseDTO responseDTO = cardService.createCard(principle.getUser(), deckId, createCardDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
}
