package com.smartmemorize.backend.deck;

import com.smartmemorize.backend.user.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeckRepository extends JpaRepository<Deck, Long> {

  List<Deck> findAllByOwner(User user);

  Optional<Deck> findByIdAndOwner(Long id, User user);
}
