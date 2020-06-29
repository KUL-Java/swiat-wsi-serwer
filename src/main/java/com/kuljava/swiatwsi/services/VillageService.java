package com.kuljava.swiatwsi.services;

import com.kuljava.swiatwsi.exceptions.VillageWithNameAlreadyExistsException;
import com.kuljava.swiatwsi.world.Village;
import com.kuljava.swiatwsi.world.VillageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VillageService {

  private final VillageRepository villageRepository;
  private final FreeCoordinatesFinderService freeCoordinatesFindersService;

  public Village saveVillage(String name) {
    Village createdVillage = createVillageWithName(name);
    villageRepository.save(createdVillage);
    return createdVillage;
  }

  private Village createVillageWithName(String name) {
    if (villageRepository.findByName(name).isPresent())
      throw new VillageWithNameAlreadyExistsException();
    return freeCoordinatesFindersService.createNextVillage(name);
  }

  public List<Village> findAllVillages() {
    return villageRepository.findAll();
  }
}
