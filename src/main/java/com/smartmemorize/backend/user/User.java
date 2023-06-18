package com.smartmemorize.backend.user;

import com.smartmemorize.backend.deck.Deck;
import com.smartmemorize.backend.deck.deckinvitation.DeckInvitation;
import com.smartmemorize.backend.deck.userdeck.UserDeck;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private String username;

  @NotBlank
  private String password;

  @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Deck> decks = new ArrayList<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<UserDeck> userDecks = new ArrayList<>();

  @OneToMany(mappedBy = "deck", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<DeckInvitation> deckInvitations = new ArrayList<>();

  public User() {
  }

  public User(User user) {
    this.id = user.id;
    this.username = user.username;
    this.password = user.password;
    this.decks = user.decks;
    this.userDecks = user.userDecks;
    this.deckInvitations = user.deckInvitations;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Deck> getDecks() {
    return decks;
  }

  public void setDecks(List<Deck> decks) {
    this.decks = decks;
  }

  public List<UserDeck> getUserDecks() {
    return userDecks;
  }

  public void setUserDecks(List<UserDeck> userDecks) {
    this.userDecks = userDecks;
  }

  public List<DeckInvitation> getDeckInvitations() {
    return deckInvitations;
  }

  public void setDeckInvitations(List<DeckInvitation> deckInvitations) {
    this.deckInvitations = deckInvitations;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(id, user.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
