package com.kuljava.swiatwsi;

import org.springframework.stereotype.Service;

import java.util.Random;

import static java.lang.StrictMath.floor;

@Service
class GeneratorService {

  int[] generateCoordinatesDifferentPattern(int maxSize, int villageNumber) {
    Random random = new Random();
    double tIncrement = 0.02;

    double horizontalOffset = 2;

    double spiralBendAngle = 2.25;
    int randomFactor = 15;
    double interval = villageNumber * tIncrement;

    int centerOffset = (maxSize / 2) - 8;

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
