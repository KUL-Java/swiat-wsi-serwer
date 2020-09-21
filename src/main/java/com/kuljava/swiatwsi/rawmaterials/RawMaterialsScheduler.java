package com.kuljava.swiatwsi.rawmaterials;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("rawmaterials.scheduler")
@Setter
public class RawMaterialsScheduler {

    @Autowired
    RawMaterialsRepository repository;
    private long woodIncrease;
    private long clayIncrease;
    private long ironIncrease;

    @Scheduled(fixedRateString = "${rawmaterials.scheduler.fixedRate}")
    public void increaseQuantities() {
        repository.increaseAll(woodIncrease, clayIncrease, ironIncrease);
    }
}


