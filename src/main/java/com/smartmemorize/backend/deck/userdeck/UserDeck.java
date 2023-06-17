package com.smartmemorize.backend.deck.userdeck;

import com.smartmemorize.backend.card.ReviewInfo;
import com.smartmemorize.backend.deck.Deck;
import com.smartmemorize.backend.user.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user_decks")
public class UserDeck {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "deck_id")
  private Deck deck;

  @OneToMany(mappedBy = "userDeck", cascade = CascadeType.ALL, orphanRemoval = true)
  private final List<ReviewInfo> reviews = new ArrayList<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserDeck userDeck = (UserDeck) o;
    return Objects.equals(id, userDeck.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
