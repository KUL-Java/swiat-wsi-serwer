package com.kuljava.swiatwsi.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.util.Collections.singleton;

@Component
@Configuration
public class CustomUserDetailsService implements UserDetailsService {

  private UserRepository userRepository;
  private PasswordEncoder passwordEncoder;

  public CustomUserDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;

  }

  public void setUserRepository(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    return Optional.ofNullable(userRepository.findByUserName(userName))
        .map(this::convertToSecurityUser)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
  }

  private org.springframework.security.core.userdetails.UserDetails convertToSecurityUser(
      User user) {
    return new org.springframework.security.core.userdetails.User(
        user.getUserName(),
        user.getPassword(),
        singleton(new SimpleGrantedAuthority("DEFAULT_ROLE")));
  }
}
