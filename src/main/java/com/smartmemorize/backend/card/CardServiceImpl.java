package com.smartmemorize.backend.card;

import com.smartmemorize.backend.card.dto.CardResponseDTO;
import com.smartmemorize.backend.card.dto.CreateCardDTO;
import com.smartmemorize.backend.deck.Deck;
import com.smartmemorize.backend.deck.DeckRepository;
import com.smartmemorize.backend.deck.exception.DeckNotFoundException;
import com.smartmemorize.backend.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;
    private final DeckRepository deckRepository;
    private final ModelMapper modelMapper;

    public CardServiceImpl(CardRepository cardRepository, DeckRepository deckRepository, ModelMapper modelMapper) {
        this.cardRepository = cardRepository;
        this.deckRepository = deckRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CardResponseDTO createCard(User user, Long deckId, CreateCardDTO createCardDTO) {
        Deck deck = deckRepository.findByIdAndOwner(deckId, user)
                .orElseThrow(() -> new DeckNotFoundException(deckId));

        Card card = modelMapper.map(createCardDTO, Card.class);
        card.setDeck(deck);

        return modelMapper.map(cardRepository.save(card), CardResponseDTO.class);
    }
}
