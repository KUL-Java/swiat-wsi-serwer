package com.kuljava.swiatwsi.services;

import com.kuljava.swiatwsi.world.Point;
import org.springframework.stereotype.Component;

import java.util.Random;

import static java.lang.StrictMath.floor;

@Component
public class VillageCoordinatesGenerator {
  private final int VILLAGE_AREA_SIZE = 100;

  Point generateSpiralCoordinates(int villageNumber) {
    double pointDensityFactor = 0.02;

    double horizontalOffset = 2;

    double spiralBendAngle = 2.25;

    double interval = villageNumber * pointDensityFactor;

    int centerOffset = 8;
    int centerTranslation = (VILLAGE_AREA_SIZE / 2) - centerOffset;

    Random random = new Random();
    int randomFactor = 15;

    int x =
        (int)
            floor(
                centerTranslation
                    + ((horizontalOffset + spiralBendAngle * interval) * Math.cos(interval))
                    + random.nextInt(randomFactor));
    int y =
        (int)
            floor(
                centerTranslation
                    + ((horizontalOffset + spiralBendAngle * interval) * Math.sin(interval))
                    + random.nextInt(randomFactor));

    return new Point(x, y);
  }
}
