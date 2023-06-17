package com.smartmemorize.backend.user;

import com.smartmemorize.backend.user.dto.CreateUserDTO;
import com.smartmemorize.backend.user.dto.UserResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  @Operation(summary = "Register a new user", description = "Register a new user. The user will automatically be logged in.")
  public ResponseEntity<UserResponseDTO> registerUser(
      @Valid @RequestBody CreateUserDTO createUserDTO,
      HttpServletRequest request,
      HttpServletResponse response) {
    UserResponseDTO responseDTO = userService.registerUser(createUserDTO, request, response);
    return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
  }

  @PostMapping("/login")
  @Operation(summary = "Login a user", description = "Login a user")
  public ResponseEntity<UserResponseDTO> loginUser(@Valid @RequestBody CreateUserDTO createUserDTO,
      HttpServletRequest request,
      HttpServletResponse response) {
    UserResponseDTO responseDTO = userService.loginUser(createUserDTO, request, response);
    return ResponseEntity.ok(responseDTO);
  }
}
