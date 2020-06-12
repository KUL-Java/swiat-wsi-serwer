package com.kuljava.swiatwsi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@SpringBootApplication
@EnableJpaRepositories
public class SwiatWsiSerwerApplication {

  public static void main(String[] args) {
    SpringApplication.run(SwiatWsiSerwerApplication.class, args);
  }

  @Bean
  CommandLineRunner runner(VillageRepository villageRepository, VillageService villageService) {
    return (args) -> {
      Village village = new Village("wies numer 1", 1, 1);

      villageRepository.save(village);

      Village village2 = new Village("wies numer 2", 3, 3);

      villageRepository.save(village2);

      Optional<Village> createdVillage = villageService.createVillageWithName("Niedrzwica");
      createdVillage.ifPresent(villageRepository::save);

    };
  }
}
