package com.kuljava.swiatwsi;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class ValidateService {

  private final int VILLAGE_AREA_SIZE = 100;

  private VillageRepository villageRepository;
  private GeneratorService generatorService;

  Village seekForFreeCoordinates(String name) {
    int existingVillages = findExistingVillagesAmount();

    int[] randomCoordinates =
        generatorService.generateCoordinatesDifferentPattern(VILLAGE_AREA_SIZE, existingVillages);
    while (checkIfVillageWithCoordinatesExists(randomCoordinates[0], randomCoordinates[1])) {
      existingVillages = findExistingVillagesAmount();
      randomCoordinates =
          generatorService.generateCoordinatesDifferentPattern(VILLAGE_AREA_SIZE, existingVillages);
    }
    return new Village(name, randomCoordinates[0], randomCoordinates[1]);
  }

  private boolean checkIfVillageWithCoordinatesExists(int x, int y) {
    return villageRepository.findByXAndY(x, y).isPresent();
  }

  private int findExistingVillagesAmount() {
    return (int) villageRepository.count();
  }
}
