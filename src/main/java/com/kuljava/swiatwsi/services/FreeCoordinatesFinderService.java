package com.kuljava.swiatwsi.services;

import com.kuljava.swiatwsi.world.Point;
import com.kuljava.swiatwsi.world.Village;
import com.kuljava.swiatwsi.world.VillageRepository;
import com.kuljava.swiatwsi.exceptions.VillagesAmountExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FreeCoordinatesFinderService {

  private final int MAXIMUM_VILLAGES_AMOUNT = 1000;
  private VillageRepository villageRepository;
  private VillageCoordinatesGenerator villageCoordinatesGenerator;
  private final List<Point> occupiedCoordinates;

  @Autowired
  public FreeCoordinatesFinderService(
      VillageRepository villageRepository,
      VillageCoordinatesGenerator villageCoordinatesGenerator) {
    this.villageRepository = villageRepository;
    this.villageCoordinatesGenerator = villageCoordinatesGenerator;
    this.occupiedCoordinates = fetchFreeCoordinatesToMemory();
  }

  Village createNextVillage(String name) {
    int existingVillages = occupiedCoordinates.size();

    if (existingVillages >= MAXIMUM_VILLAGES_AMOUNT) {
      throw new VillagesAmountExceededException();
    }

    Point randomCoordinates = findNextFreeSpot();
    occupiedCoordinates.add(randomCoordinates);

    return new Village(name, randomCoordinates);
  }

  private Point findNextFreeSpot() {
    int existingVillages = occupiedCoordinates.size();
    Point randomCoordinates =
        villageCoordinatesGenerator.generateCoordinates(existingVillages);

    while (areCoordinatesTaken(randomCoordinates)) {
      existingVillages++;
      randomCoordinates = villageCoordinatesGenerator.generateCoordinates(existingVillages);
    }
    return randomCoordinates;
  }

  private boolean areCoordinatesTaken(Point coordinates) {
    return occupiedCoordinates.contains(coordinates);
  }

  private List<Point> fetchFreeCoordinatesToMemory() {
    return villageRepository.findAll().stream().map(Village::getPoint).collect(Collectors.toList());
  }
}
