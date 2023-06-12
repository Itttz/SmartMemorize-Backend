package com.smartmemorize.backend.deck.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateDeckDTO {
    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CreateDeckDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
