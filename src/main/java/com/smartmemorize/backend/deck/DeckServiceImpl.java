package com.smartmemorize.backend.deck;

import com.smartmemorize.backend.deck.dto.CreateDeckDTO;
import com.smartmemorize.backend.deck.dto.DeckResponseDTO;
import com.smartmemorize.backend.user.User;
import org.modelmapper.ModelMapper;

public class DeckServiceImpl implements DeckService {
    private final DeckRepository deckRepository;
    private final ModelMapper modelMapper;

    public DeckServiceImpl(DeckRepository deckRepository, ModelMapper modelMapper) {
        this.deckRepository = deckRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DeckResponseDTO createDeck(User user, CreateDeckDTO createDeckDTO) {
        Deck deck = new Deck();
        deck.setName(createDeckDTO.getName());
        deck.setOwner(user);

        return modelMapper.map(deckRepository.save(deck), DeckResponseDTO.class);
    }
}