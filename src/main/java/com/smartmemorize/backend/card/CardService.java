package com.smartmemorize.backend.card;

import com.smartmemorize.backend.card.dto.CardResponseDTO;
import com.smartmemorize.backend.card.dto.CreateCardDTO;
import com.smartmemorize.backend.user.User;

public interface CardService {
    CardResponseDTO createCard(User user, Long deckId, CreateCardDTO createCardDTO);
}
