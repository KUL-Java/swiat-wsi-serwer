package com.kuljava.swiatwsi.services;

import com.kuljava.swiatwsi.world.Point;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Random;

@Primary
@Component
public class RandomVillageCoordinatesGenerator implements VillageCoordinatesGenerator {

  private final int VILLAGE_AREA_SIZE = 100;

  private final Random random;

  public RandomVillageCoordinatesGenerator() {
    this.random = new Random();
  }

  @Override
  public Point generateCoordinates(int villageNumber) {
    int generatedXCoordinate = random.nextInt(VILLAGE_AREA_SIZE);
    int generatedYCoordinate = random.nextInt(VILLAGE_AREA_SIZE);
    return new Point(generatedXCoordinate, generatedYCoordinate);
  }
}
