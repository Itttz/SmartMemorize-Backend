package com.smartmemorize.backend.deck.deckinvitation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeckInvitationRepository extends JpaRepository<DeckInvitation, Long> {
}
