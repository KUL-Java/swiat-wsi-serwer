package com.kuljava.swiatwsi;

import com.kuljava.swiatwsi.security.User;
import com.kuljava.swiatwsi.security.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableScheduling
public class SwiatWsiSerwerApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext run =
        SpringApplication.run(SwiatWsiSerwerApplication.class, args);
    UserService userService = run.getBean(UserService.class);
    User user = new User();
    user.setUserName("dandoo");
    user.setPassword("password");
    user.setEmail("damian.michalak@gmail.com");
    userService.addWithDefaultRole(user); // this lines will create default user
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }
}
