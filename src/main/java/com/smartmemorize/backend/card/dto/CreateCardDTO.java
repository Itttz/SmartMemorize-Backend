package com.smartmemorize.backend.card.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateCardDTO {
    @NotBlank
    public String front;
    public String back;

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

    @Override
    public String toString() {
        return "CreateCardDTO{" +
                "front='" + front + '\'' +
                ", back='" + back + '\'' +
                '}';
    }
}
