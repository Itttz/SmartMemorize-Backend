package com.smartmemorize.backend.card;

import com.smartmemorize.backend.deck.UserDeck;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "reviews")
public class ReviewInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_deck_id")
    private UserDeck userDeck;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewInfo that = (ReviewInfo) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
