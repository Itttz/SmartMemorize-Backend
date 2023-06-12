package com.smartmemorize.backend.user;

import com.smartmemorize.backend.user.dto.CreateUserDTO;
import com.smartmemorize.backend.user.dto.UserResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {
    UserResponseDTO registerUser(CreateUserDTO createUserDTO,
                                 HttpServletRequest request,
                                 HttpServletResponse response);

    UserResponseDTO loginUser(CreateUserDTO createUserDTO,
                              HttpServletRequest request,
                              HttpServletResponse response);
}
