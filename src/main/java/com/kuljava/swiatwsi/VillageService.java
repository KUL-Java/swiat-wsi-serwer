package com.kuljava.swiatwsi;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

import static java.lang.StrictMath.floor;

@Service
public class VillageService {

  private final VillageRepository villageRepository;

  public VillageService(VillageRepository villageRepository) {
    this.villageRepository = villageRepository;
  }

  void saveVillage(String name) {
    createVillageWithName(name).ifPresent(villageRepository::save);
  }

  Optional<Village> createVillageWithName(String name) {
    if (villageRepository.findByName(name).isPresent()) return Optional.empty();
    return Optional.of(seekForFreeCoordinates(name));
  }

  private Village seekForFreeCoordinates(String name) {
    int existingVillages = findExistingVillagesAmount();
    int VILLAGE_AREA_SIZE = 50;
    int[] randomCoordinates =
        generateCoordinatesDifferentPattern(VILLAGE_AREA_SIZE, existingVillages);
    while (checkIfVillageWithCoordinatesExists(randomCoordinates[0], randomCoordinates[1])) {
      existingVillages = findExistingVillagesAmount();
      randomCoordinates = generateCoordinatesDifferentPattern(existingVillages, VILLAGE_AREA_SIZE);
    }
    return new Village(name, randomCoordinates[0], randomCoordinates[1]);
  }

  private boolean checkIfVillageWithCoordinatesExists(int x, int y) {
    return villageRepository.findByXAndY(x, y).isPresent();
  }

  private int[] generateCoordinates(int maxSize) {
    Random random = new Random();
    return new int[] {random.nextInt(maxSize), random.nextInt(maxSize)};
  }

  private int findExistingVillagesAmount() {
    return (int) villageRepository.count();
  }

  private int[] generateCoordinatesDifferentPattern(int maxSize, int villageNumber) {
    Random random = new Random();
    double tIncrement = 0.025;

    // przesuniecie osi x
    float horizontalOffset = 8;

    // kat spirali
    float spiralBendAngle = 1;
    int randomFactor = 1;

    tIncrement = villageNumber * tIncrement;
    double interval = villageNumber * tIncrement;

    int centerOffset = maxSize / 2;

    int x =
        (int)
            floor(
                centerOffset
                    + ((horizontalOffset + spiralBendAngle * interval) * Math.cos(interval))
                    + random.nextInt(randomFactor));
    int y =
        (int)
            floor(
                centerOffset
                    + ((horizontalOffset + spiralBendAngle * interval) * Math.sin(interval))
                    + random.nextInt(randomFactor));

    return new int[] {x, y};
  }
}
