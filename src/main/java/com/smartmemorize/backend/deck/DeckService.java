package com.smartmemorize.backend.deck;

import com.smartmemorize.backend.deck.dto.CreateDeckDTO;
import com.smartmemorize.backend.deck.dto.DeckResponseDTO;
import com.smartmemorize.backend.user.User;

public interface DeckService {
    DeckResponseDTO createDeck(User user, CreateDeckDTO createDeckDTO);
}
