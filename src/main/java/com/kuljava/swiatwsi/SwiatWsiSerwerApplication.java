package com.kuljava.swiatwsi;

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
    DbFiller.fillDbWithData(run); // TODO data.sql gives relation does not exists error
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }
}
