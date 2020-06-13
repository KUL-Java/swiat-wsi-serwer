package com.kuljava.swiatwsi;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

import static java.lang.Integer.SIZE;
import static java.lang.StrictMath.floor;

@Service
public class VillageService {
  boolean going;

  private VillageRepository villageRepository;

  public VillageService(VillageRepository villageRepository) {
    this.villageRepository = villageRepository;
  }

  public Optional<Village> createVillageWithName(String name, int t) {
    if (villageRepository.findByName(name).isPresent()) return Optional.empty();
    return Optional.of(generateVillage(name, t));
  }

  private Village generateVillage(String name, int t) {
    int[] randomCoords;
    do {

      randomCoords = generateCoordinatesDifferentPattern(t);
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

  private int[] generateCoordinatesDifferentPattern(int t) {
    Random random = new Random();
    final int villageAreaSize = 25;
    int x = 0;
    int y = 0;
    double tIncrement = 0.025;
    tIncrement = t * tIncrement;
    float spiral_a = 8;
    float spiral_b = 4;
    int randomFactor = 20;

    x =
        (int)
            floor(
                villageAreaSize / 2
                    + ((spiral_a + spiral_b * t) * Math.cos(tIncrement))
                    + random.nextInt(randomFactor));
    y =
        (int)
            floor(
                villageAreaSize / 2
                    + ((spiral_a + spiral_b * t) * Math.sin(tIncrement))
                    + random.nextInt(randomFactor));

    return new int[] {x, y};
  }
}
