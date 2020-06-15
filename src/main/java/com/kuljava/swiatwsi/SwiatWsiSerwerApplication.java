package com.kuljava.swiatwsi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@SpringBootApplication
@EnableJpaRepositories
public class SwiatWsiSerwerApplication {

  public static void main(String[] args) {
    SpringApplication.run(SwiatWsiSerwerApplication.class, args);
  }

  @Bean
  CommandLineRunner runner(VillageRepository villageRepository, VillageService villageService) {
    return (args) -> {
      Village village = villageService.createVillageWithName("Krasnik").get();

      villageRepository.save(village);

      Village village2 = villageService.createVillageWithName("Urzedow ").get();

      villageRepository.save(village2);

      Optional<Village> createdVillage = villageService.createVillageWithName("Niedrzwica");
      createdVillage.ifPresent(villageRepository::save);

      dajNazw(20).forEach(villageService::saveVillage);


    };
  }

  private List<String> dajNazw(int ile){
    List<String> lista = new ArrayList<>();

    for (int i = 0; i < ile; i++) {
      lista.add(dajNazwe());
    }

    return lista;
  }

  private String dajNazwe(){
    return String.valueOf(new Random().nextDouble());
  }
}
