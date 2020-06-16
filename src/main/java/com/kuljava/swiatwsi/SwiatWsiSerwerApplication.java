package com.kuljava.swiatwsi;

import com.kuljava.swiatwsi.exceptions.VillageWithNameAlreadyExistsException;
import com.kuljava.swiatwsi.exceptions.VillagesAmountExceededException;
import com.kuljava.swiatwsi.services.VillageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

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
