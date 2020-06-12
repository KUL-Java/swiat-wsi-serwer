package com.kuljava.swiatwsi;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class VillageService {

  private final int VILLAGES_AREA_SIZE = 25;

  private VillageRepository villageRepository;

  public VillageService(VillageRepository villageRepository) {
    this.villageRepository = villageRepository;
  }

  public Optional<Village> createVillageWithName(String name) {
    if (villageRepository.findByName(name).isPresent()) return Optional.empty();
    return Optional.of(generateVillage(name));
  }

  private Village generateVillage(String name) {
    int[] randomCoords;
    do {
      randomCoords = generateCoordinates(VILLAGES_AREA_SIZE);
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
}
