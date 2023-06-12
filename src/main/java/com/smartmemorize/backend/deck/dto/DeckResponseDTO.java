package com.smartmemorize.backend.deck.dto;

public class DeckResponseDTO {
    public Long id;
    public String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DeckResponseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
