package com.smartmemorize.backend.deck.userdeck;

import com.smartmemorize.backend.deck.Deck;
import com.smartmemorize.backend.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDeckRepository extends JpaRepository<UserDeck, Long> {

  boolean existsByUserAndDeck(User user, Deck deck);
}
