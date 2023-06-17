package com.smartmemorize.backend.card.dto;

public class CardResponseDTO {

  private Long id;
  private String front;
  private String back;

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

  @Override
  public String toString() {
    return "CardResponseDTO{" +
        "id=" + id +
        ", front='" + front + '\'' +
        ", back='" + back + '\'' +
        '}';
  }
}
