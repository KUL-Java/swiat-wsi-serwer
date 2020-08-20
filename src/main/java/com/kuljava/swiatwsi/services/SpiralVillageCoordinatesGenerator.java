package com.kuljava.swiatwsi.services;

import com.kuljava.swiatwsi.world.Point;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Random;

import static java.lang.StrictMath.floor;

@Primary
@Component
public class SpiralVillageCoordinatesGenerator implements VillageCoordinatesGenerator {
  private final int VILLAGE_AREA_SIZE = 100;

  private final double horizontalOffset = 2;

  private final double spiralBendAngle = 2.25;

  private final int centerOffset = 8;
  private final int centerTranslation = (VILLAGE_AREA_SIZE / 2) - centerOffset;

  private Random random = new Random();
  private final int randomFactor = 15;

  public Point generateCoordinates(int villageNumber) {
    double pointDensityFactor = 0.02;
    final double interval = villageNumber * pointDensityFactor;

    return new Point(generateXCoordinate(interval), generateYCoordinate(interval));
  }

  private int generateXCoordinate(double interval) {

    return (int)
        floor(
            centerTranslation
                + ((horizontalOffset + spiralBendAngle * interval) * Math.cos(interval))
                + random.nextInt(randomFactor));
  }

  private int generateYCoordinate(double interval) {

    return (int)
        floor(
            centerTranslation
                + ((horizontalOffset + spiralBendAngle * interval) * Math.sin(interval))
                + random.nextInt(randomFactor));
  }
}
