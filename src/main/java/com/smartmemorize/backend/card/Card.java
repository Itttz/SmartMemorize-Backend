package com.smartmemorize.backend.card;

import com.smartmemorize.backend.deck.Deck;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cards")
public class Card {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private String front;

  @NotBlank
  private String back;

  @ManyToOne
  @JoinColumn(name = "deck_id")
  private Deck deck;
  @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ReviewInfo> reviews = new ArrayList<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFront() {
    return front;
  }

  public void setFront(String front) {
    this.front = front;
  }

  public String getBack() {
    return back;
  }

  public void setBack(String back) {
    this.back = back;
  }

  public Deck getDeck() {
    return deck;
  }

  public void setDeck(Deck deck) {
    this.deck = deck;
  }

  public List<ReviewInfo> getReviews() {
    return reviews;
  }

  public void setReviews(List<ReviewInfo> reviews) {
    this.reviews = reviews;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Card card = (Card) o;
    return Objects.equals(id, card.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
