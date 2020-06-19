package com.kuljava.swiatwsi.services;

import com.kuljava.swiatwsi.world.Point;
import com.kuljava.swiatwsi.world.Village;
import com.kuljava.swiatwsi.world.VillageRepository;
import com.kuljava.swiatwsi.exceptions.VillagesAmountExceededException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ValidateService {

  private final int MAXIMUM_VILLAGES_AMOUNT = 1000;
  private final int VILLAGE_AREA_SIZE = 100;

  private VillageRepository villageRepository;
  private VillageCoordinatesGenerator villageCoordinatesGenerator;

  Village seekForFreeCoordinates(String name) {
    int existingVillages = findExistingVillagesAmount();

    if(existingVillages > MAXIMUM_VILLAGES_AMOUNT) throw new VillagesAmountExceededException();

    Point randomCoordinates =
        villageCoordinatesGenerator.generateCoordinatesDifferentPattern(VILLAGE_AREA_SIZE, existingVillages);

    while (checkIfVillageWithCoordinatesExists(randomCoordinates)) {
      existingVillages = findExistingVillagesAmount();
      randomCoordinates =
          villageCoordinatesGenerator.generateCoordinatesDifferentPattern(VILLAGE_AREA_SIZE, existingVillages);
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
