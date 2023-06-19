package com.smartmemorize.backend.deck.deckinvitation;

import com.smartmemorize.backend.deck.Deck;
import com.smartmemorize.backend.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "deck_invitations")
public class DeckInvitation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "deck_id")
  private Deck deck;

  @Enumerated(EnumType.STRING)
  private DeckInvitationStatus status = DeckInvitationStatus.PENDING;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Deck getDeck() {
    return deck;
  }

  public void setDeck(Deck deck) {
    this.deck = deck;
  }

  public DeckInvitationStatus getStatus() {
    return status;
  }

  public void setStatus(DeckInvitationStatus status) {
    this.status = status;
  }

  @Override
  public boolean equals(Object o) {
      if (this == o) {
          return true;
      }
      if (o == null || getClass() != o.getClass()) {
          return false;
      }
    DeckInvitation that = (DeckInvitation) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "DeckInvitation{" +
        "id=" + id +
        ", user=" + user.getUsername() +
        ", deck=" + deck.getName() +
        ", status=" + status +
        '}';
  }
}
