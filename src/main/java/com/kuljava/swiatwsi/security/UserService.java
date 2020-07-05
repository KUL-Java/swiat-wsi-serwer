package com.kuljava.swiatwsi.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(
      UserRepository userRepository,
      PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public void addWithDefaultRole(User user) {
    user.setRole("DEFAULT_ROLE");
    String passwordHash = passwordEncoder.encode(user.getPassword());
    user.setPassword(passwordHash);
    userRepository.save(user);
  }
}
