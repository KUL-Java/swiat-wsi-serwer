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
      Village village = villageService.createVillageWithName("Krasnik ", 1).get();

      villageRepository.save(village);

      Village village2 = villageService.createVillageWithName("Urzedow ", 2).get();

      villageRepository.save(village2);

      Optional<Village> createdVillage = villageService.createVillageWithName("Niedrzwica", 3);
      createdVillage.ifPresent(villageRepository::save);
    };
  }
}
