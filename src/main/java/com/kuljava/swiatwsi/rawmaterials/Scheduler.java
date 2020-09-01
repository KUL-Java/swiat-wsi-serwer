package com.kuljava.swiatwsi.rawmaterials;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

  @Autowired RawMaterialsRepository repository;

  private Long woodIncrease;
  private Long clayIncrease;
  private Long ironIncrease;

  public Scheduler(
      @Value("${rawmaterials.scheduler.woodincrease}") String woodIncrease,
      @Value("${rawmaterials.scheduler.clayincrease}") String clayIncrease,
      @Value("${rawmaterials.scheduler.ironincrease}") String ironIncrease) {
    this.woodIncrease = Long.parseLong(woodIncrease);
    this.clayIncrease = Long.parseLong(clayIncrease);
    this.ironIncrease = Long.parseLong(ironIncrease);
  }

  @Scheduled(fixedRateString = "${rawmaterials.scheduler.fixedRate}")
  public void increaseQuantities() {
    repository.increaseAll(woodIncrease, clayIncrease, ironIncrease);
  }
}
