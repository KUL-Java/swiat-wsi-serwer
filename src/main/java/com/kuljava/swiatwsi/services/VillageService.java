package com.kuljava.swiatwsi.services;

import com.kuljava.swiatwsi.world.Village;
import com.kuljava.swiatwsi.world.VillageRepository;
import com.kuljava.swiatwsi.exceptions.VillageWithNameAlreadyExistsException;
import com.kuljava.swiatwsi.exceptions.VillagesAmountExceededException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VillageService {

  private final VillageRepository villageRepository;
  private final ValidateService validateService;

  public Village saveVillage(String name) {
    Village createdVillage = createVillageWithName(name);
    villageRepository.save(createdVillage);
    return createdVillage;
  }

  private Village createVillageWithName(String name) {
    if (villageRepository.findByName(name).isPresent())
      throw new VillageWithNameAlreadyExistsException();
    return validateService.seekForFreeCoordinates(name);
  }

  public List<Village> findAllVillages() {
    return villageRepository.findAll();
  }
}
