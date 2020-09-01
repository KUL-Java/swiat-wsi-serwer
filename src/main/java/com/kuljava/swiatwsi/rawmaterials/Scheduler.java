package com.kuljava.swiatwsi.rawmaterials;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
    private Long woodIncrease;
    private Long clayIncrease;
    private Long ironIncrease;

    public Scheduler() {
        woodIncrease = 10l;
        clayIncrease = 10l;
        ironIncrease = 10l;
    }


    @Autowired
    RawMaterialsRepository repository;

    @Scheduled(fixedRateString = "${rawmaterials.scheduler.fixedRate}")
    public void increaseQuantities() {
        repository.increaseAll(woodIncrease, clayIncrease, ironIncrease);
    }
}