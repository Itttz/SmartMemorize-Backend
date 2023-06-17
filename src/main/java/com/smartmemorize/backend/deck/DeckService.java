package com.smartmemorize.backend.deck;

import com.smartmemorize.backend.deck.dto.CreateDeckDTO;
import com.smartmemorize.backend.deck.dto.DeckResponseDTO;
import com.smartmemorize.backend.user.User;
import java.util.List;

public interface DeckService {

  DeckResponseDTO createDeck(User user, CreateDeckDTO createDeckDTO);

  List<DeckResponseDTO> getAllDecks(User user);

  void inviteUserToDeck(User user, Long deckId, Long userId);
}
