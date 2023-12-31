package com.smartmemorize.backend.user.dto;

public class UserResponseDTO {

  private Long id;
  private String username;

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

  @Override
  public String toString() {
    return "UserResponseDTO{" +
        "id=" + id +
        ", username='" + username + '\'' +
        '}';
  }
}
