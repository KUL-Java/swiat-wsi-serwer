package com.kuljava.swiatwsi;

import com.kuljava.swiatwsi.security.User;
import com.kuljava.swiatwsi.security.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SwiatWsiSerwerApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext run = SpringApplication.run(SwiatWsiSerwerApplication.class, args);
    UserService userService = run.getBean(UserService.class);
    User user = new User();
    user.setUserName("dandoo");
    user.setPassword("password");
    user.setEmail("damian.michalak@gmail.com");
    userService.addWithDefaultRole(user);

    User user2 = new User();
    user2.setUserName("michal");
    user2.setPassword("password");
    user2.setEmail("kolothej@gmail.com");
    userService.addWithDefaultRole(user2);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }


}
