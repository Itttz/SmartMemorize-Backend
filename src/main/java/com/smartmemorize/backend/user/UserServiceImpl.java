package com.smartmemorize.backend.user;

import com.smartmemorize.backend.security.UserPrincipal;
import com.smartmemorize.backend.user.dto.CreateUserDTO;
import com.smartmemorize.backend.user.dto.UserResponseDTO;
import com.smartmemorize.backend.user.exception.UserExistsException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final ModelMapper modelMapper;
  private final SecurityContextRepository securityContextRepository;
  private final SecurityContextHolderStrategy securityContextHolderStrategy;

  public UserServiceImpl(UserRepository userRepository,
      BCryptPasswordEncoder passwordEncoder,
      AuthenticationManager authenticationManager,
      ModelMapper modelMapper,
      SecurityContextRepository securityContextRepository,
      SecurityContextHolderStrategy securityContextHolderStrategy) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.authenticationManager = authenticationManager;
    this.modelMapper = modelMapper;
    this.securityContextRepository = securityContextRepository;
    this.securityContextHolderStrategy = securityContextHolderStrategy;
  }

  @Override
  public UserResponseDTO registerUser(CreateUserDTO createUserDTO,
      HttpServletRequest request,
      HttpServletResponse response) {
    if (userRepository.existsByUsername(createUserDTO.getUsername())) {
      throw new UserExistsException(createUserDTO.getUsername());
    }

    String temp = createUserDTO.getPassword();
    createUserDTO.setPassword(passwordEncoder.encode(temp));
    User user = userRepository.save(modelMapper.map(createUserDTO, User.class));

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(createUserDTO.getUsername(), temp));
    setAuthenticationContext(authentication, request, response);

    return modelMapper.map(user, UserResponseDTO.class);
  }

  @Override
  public UserResponseDTO loginUser(CreateUserDTO createUserDTO,
      HttpServletRequest request,
      HttpServletResponse response) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(createUserDTO.getUsername(),
            createUserDTO.getPassword())
    );
    setAuthenticationContext(authentication, request, response);
    User user = (User) authentication.getPrincipal();
    return modelMapper.map(user, UserResponseDTO.class);
  }

  private void setAuthenticationContext(Authentication authentication,
      HttpServletRequest request,
      HttpServletResponse response) {
    SecurityContext context = securityContextHolderStrategy.createEmptyContext();
    context.setAuthentication(authentication);
    securityContextHolderStrategy.setContext(context);
    securityContextRepository.saveContext(context, request, response);
  }
}
