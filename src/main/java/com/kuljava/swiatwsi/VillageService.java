package com.kuljava.swiatwsi;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

import static java.lang.Integer.SIZE;
import static java.lang.StrictMath.floor;

@Service
public class VillageService {

  private final int VILLAGE_AREA_SIZE = 50;

  private final VillageRepository villageRepository;

  public VillageService(VillageRepository villageRepository) {
    this.villageRepository = villageRepository;
  }

  public void createVillage(String name){
    createVillageWithName(name).ifPresent(villageRepository::save);
  }


  public Optional<Village> createVillageWithName(String name) {
    if (villageRepository.findByName(name).isPresent()) return Optional.empty();
    return Optional.of(generateVillage(name));
  }

  private Village generateVillage(String name) {
    //todo: wyjebac
    int[] randomCoords;
    do {
      int existingVillages = findExistingVillagesAmount();
      randomCoords = generateCoordinatesDifferentPattern(existingVillages, VILLAGE_AREA_SIZE);
    } while (villageWithCoordinatesExists(randomCoords[0], randomCoords[1]));

    return new Village(name, randomCoords[0], randomCoords[1]);
  }

  public boolean villageWithCoordinatesExists(int x, int y) {
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

    //przesuniecie osi x
    float horizontalOffset = 8;

    //kat spirali
    float spiralBendAngle = 1;
    int randomFactor = 1;

    tIncrement = villageNumber * tIncrement;
    double interval  = villageNumber * tIncrement;

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
