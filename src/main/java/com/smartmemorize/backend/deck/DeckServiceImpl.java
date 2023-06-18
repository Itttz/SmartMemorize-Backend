package com.smartmemorize.backend.deck;

import com.smartmemorize.backend.deck.deckinvitation.DeckInvitation;
import com.smartmemorize.backend.deck.deckinvitation.DeckInvitationRepository;
import com.smartmemorize.backend.deck.dto.CreateDeckDTO;
import com.smartmemorize.backend.deck.dto.DeckResponseDTO;
import com.smartmemorize.backend.deck.exception.DeckNotFoundException;
import com.smartmemorize.backend.deck.exception.UserAlreadyInDeckException;
import com.smartmemorize.backend.deck.userdeck.UserDeckRepository;
import com.smartmemorize.backend.user.User;
import com.smartmemorize.backend.user.UserRepository;
import com.smartmemorize.backend.user.exception.UserNotFoundException;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class DeckServiceImpl implements DeckService {

  private final DeckInvitationRepository deckInvitationRepository;
  private final DeckRepository deckRepository;
  private final UserRepository userRepository;
  private final UserDeckRepository userDeckRepository;
  private final ModelMapper modelMapper;

  public DeckServiceImpl(DeckInvitationRepository deckInvitationRepository,
      DeckRepository deckRepository,
      UserRepository userRepository,
      UserDeckRepository userDeckRepository,
      ModelMapper modelMapper) {
    this.deckInvitationRepository = deckInvitationRepository;
    this.deckRepository = deckRepository;
    this.userRepository = userRepository;
    this.userDeckRepository = userDeckRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public DeckResponseDTO createDeck(User user, CreateDeckDTO createDeckDTO) {
    Deck deck = new Deck();
    deck.setName(createDeckDTO.getName());
    deck.setOwner(user);

    return modelMapper.map(deckRepository.save(deck), DeckResponseDTO.class);
  }

  @Override
  public List<DeckResponseDTO> getAllDecks(User user) {
    return deckRepository.findAllByOwner(user)
        .stream()
        .map(deck -> modelMapper.map(deck, DeckResponseDTO.class))
        .toList();
  }

  @Override
  public void inviteUserToDeck(User user, Long deckId, Long userId) {
    Deck deck = deckRepository.findByIdAndOwner(deckId, user)
        .orElseThrow(() -> new DeckNotFoundException(deckId));

    User recipient = userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException(userId));

    if (userDeckRepository.existsByUserAndDeck(recipient, deck)) {
      throw new UserAlreadyInDeckException();
    }

    DeckInvitation invitation = new DeckInvitation();
    invitation.setDeck(deck);
    invitation.setUser(recipient);

    deckInvitationRepository.save(invitation);
  }
}
