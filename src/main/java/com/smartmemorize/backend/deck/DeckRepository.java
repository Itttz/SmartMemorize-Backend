package com.smartmemorize.backend.deck;

import com.smartmemorize.backend.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeckRepository extends JpaRepository<Deck, Long> {
    List<Deck> findAllByOwner(User user);

    Optional<Deck> findByIdAndOwner(Long id, User user);
}
