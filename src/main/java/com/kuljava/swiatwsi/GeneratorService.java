package com.kuljava.swiatwsi;


import org.springframework.stereotype.Service;

import java.util.Random;

import static java.lang.StrictMath.floor;
@Service
class GeneratorService {

  int[] generateCoordinatesDifferentPattern(int maxSize, int villageNumber) {
    Random random = new Random();
    double tIncrement = 0.025;

    // przesuniecie osi x
    float horizontalOffset = 8;

    // kat spirali
    float spiralBendAngle = (float) 0.25;
    int randomFactor = 3;
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
