package com.kuljava.swiatwsi.services;

import com.kuljava.swiatwsi.world.Point;
import com.kuljava.swiatwsi.world.Village;
import com.kuljava.swiatwsi.world.VillageRepository;
import com.kuljava.swiatwsi.exceptions.VillagesAmountExceededException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FreeCoordinatesFindersService {

  private final int MAXIMUM_VILLAGES_AMOUNT = 1000;

  private VillageRepository villageRepository;
  private VillageCoordinatesGenerator villageCoordinatesGenerator;

  public Village createNextVillage(String name) {
    int existingVillages = findExistingVillagesAmount();

    if (existingVillages > MAXIMUM_VILLAGES_AMOUNT) throw new VillagesAmountExceededException();

    Point randomCoordinates = findNextFreeSpot();

    return new Village(name, randomCoordinates.getX(), randomCoordinates.getY());
  }

  private boolean isVillageWithCoordinatesExisting(Point coordinates) {
    return villageRepository.findByXAndY(coordinates.getX(), coordinates.getY()).isPresent();
  }

  private int findExistingVillagesAmount() {
    return (int) villageRepository.count();
  }

  private Point findNextFreeSpot() {
    int existingVillages = findExistingVillagesAmount();
    Point randomCoordinates =
        villageCoordinatesGenerator.generateSpiralCoordinates(existingVillages);

    while (isVillageWithCoordinatesExisting(randomCoordinates)) {
      existingVillages = findExistingVillagesAmount();
      randomCoordinates = villageCoordinatesGenerator.generateSpiralCoordinates(existingVillages);
    }
    return randomCoordinates;
  }
}
