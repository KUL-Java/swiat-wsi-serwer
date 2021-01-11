package com.kuljava.swiatwsi.services;

import com.kuljava.swiatwsi.security.User;
import com.kuljava.swiatwsi.world.Village;
import com.kuljava.swiatwsi.world.VillageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VillageService {

  private final VillageRepository villageRepository;
  private final FreeCoordinatesFinderService freeCoordinatesFindersService;

  public Optional<Village> saveVillage(String name) {
    Optional<Village> createdVillage = createVillageWithName(name);
    createdVillage.ifPresent(villageRepository::save);
    return Optional.empty();
  }

  private Optional<Village> createVillageWithName(String name) {
    if (villageRepository.findByName(name).isPresent()) {
      return Optional.empty();
    }
    return Optional.ofNullable(freeCoordinatesFindersService.createNextVillage(name));
  }

  public List<Village> findAllVillages() {
    return villageRepository.findAll();
  }


  public Optional<Village> findVillageForUser(String userName) {
    return villageRepository.findByusername(userName);
  }
}
