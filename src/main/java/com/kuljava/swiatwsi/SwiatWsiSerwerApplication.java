package com.kuljava.swiatwsi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@SpringBootApplication
@EnableJpaRepositories
public class SwiatWsiSerwerApplication {

  public static void main(String[] args) {
    SpringApplication.run(SwiatWsiSerwerApplication.class, args);
  }

  @Bean
  CommandLineRunner runner(VillageService villageService) {
    return (args) -> {
      for (int i = 1; i < 999; i++) {
        try {
          villageService.saveVillage("Niedrzwica " + i);
        } catch (VillagesAmountExceededException | VillageWithNameAlreadyExistsException e) {
          System.out.println("Niestety, " + e.getMessage());
        }
      }
    };
  }
}
