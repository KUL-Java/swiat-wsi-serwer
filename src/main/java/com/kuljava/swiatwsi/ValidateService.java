package com.kuljava.swiatwsi;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
class ValidateService {

  private final int MAXIMUM_VILLAGES_AMOUNT = 1000;
  private final int VILLAGE_AREA_SIZE = 100;

  private VillageRepository villageRepository;
  private GeneratorService generatorService;

  Village seekForFreeCoordinates(String name) throws VillagesAmountExceededException {
    int existingVillages = findExistingVillagesAmount();

    if(existingVillages > MAXIMUM_VILLAGES_AMOUNT) throw new VillagesAmountExceededException();

    Point randomCoordinates =
        generatorService.generateCoordinatesDifferentPattern(VILLAGE_AREA_SIZE, existingVillages);

    while (checkIfVillageWithCoordinatesExists(randomCoordinates)) {
      existingVillages = findExistingVillagesAmount();
      randomCoordinates =
          generatorService.generateCoordinatesDifferentPattern(VILLAGE_AREA_SIZE, existingVillages);
    }

    return new Village(name, randomCoordinates.getX(), randomCoordinates.getY());
  }

  private boolean checkIfVillageWithCoordinatesExists(Point coordinates) {
    return villageRepository.findByXAndY(coordinates.getX(), coordinates.getY()).isPresent();
  }

  private int findExistingVillagesAmount() {
    return (int) villageRepository.count();
  }

}
