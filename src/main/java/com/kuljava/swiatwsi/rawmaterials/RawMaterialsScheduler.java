package com.kuljava.swiatwsi.rawmaterials;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties("rawmaterials.scheduler")
@Setter
public class RawMaterialsScheduler {

    private RawMaterialsRepository repository;
    private long woodIncrease;
    private long clayIncrease;
    private long ironIncrease;

    public RawMaterialsScheduler(RawMaterialsRepository repository) {
        this.repository = repository;
    }

    @Scheduled(fixedRateString = "${rawmaterials.scheduler.fixedRate}")
    public void increaseQuantitiesByDefaultValue() {
        List<RawMaterials> rawMaterialsList = repository.findAll();
        rawMaterialsList.forEach(r -> {
            r.setIronQuantity(r.getIronQuantity() + ironIncrease);
            r.setWoodQuantity(r.getWoodQuantity() + woodIncrease);
            r.setClayQuantity(r.getClayQuantity() + clayIncrease);
        });
        repository.saveAll(rawMaterialsList);
    }
}


