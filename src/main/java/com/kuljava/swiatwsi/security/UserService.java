package com.kuljava.swiatwsi.security;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public void addWithDefaultRole(User user) {
    user.setRole("DEFAULT_ROLE");
    String passwordHash = passwordEncoder.encode(user.getPassword());
    user.setPassword(passwordHash);
    userRepository.save(user);
  }

  public Optional<User> findUserByUsername(String userName){
    return userRepository.findByUserName(userName);
  }
}
