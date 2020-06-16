package com.kuljava.swiatwsi;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
class VillageService {

  private final VillageRepository villageRepository;
  private final ValidateService validateService;

  void saveVillage(String name) {
    createVillageWithName(name).ifPresent(villageRepository::save);
  }

  Optional<Village> createVillageWithName(String name) {
    if (villageRepository.findByName(name).isPresent()) return Optional.empty();
    return Optional.of(validateService.seekForFreeCoordinates(name));
  }
}
