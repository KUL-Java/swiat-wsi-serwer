package com.kuljava.swiatwsi;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
class VillageService {

  private final VillageRepository villageRepository;
  private final ValidateService validateService;

  public Village saveVillage(String name) throws VillagesAmountExceededException, VillageWithNameAlreadyExistsException {
    Village createdVillage = createVillageWithName(name);
    villageRepository.save(createdVillage);
    return createdVillage;
  }

  private Village createVillageWithName(String name) throws VillagesAmountExceededException, VillageWithNameAlreadyExistsException {
    if (villageRepository.findByName(name).isPresent()) throw new VillageWithNameAlreadyExistsException();
    return validateService.seekForFreeCoordinates(name);
  }

  public List<Village> findAllVillages() {
    return villageRepository.findAll();
  }
}
