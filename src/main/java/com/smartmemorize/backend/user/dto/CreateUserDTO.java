package com.smartmemorize.backend.user.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateUserDTO {

  @NotBlank
  private String username;

  @NotBlank
  private String password;

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

  @Override
  public String toString() {
    return "CreateUserDTO{" +
        "username='" + username + '\'' +
        ", password='" + password + '\'' +
        '}';
  }
}
