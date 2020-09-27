package com.kuljava.swiatwsi.rawmaterials;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
    public void increaseQuantitiesByDefaultValue() {
        List<RawMaterials> all = repository.findAll();
        List<RawMaterials> increased = all.stream().map(r -> {
            r.setIronQuantity(r.getIronQuantity() + ironIncrease);
            r.setWoodQuantity(r.getWoodQuantity() + woodIncrease);
            r.setClayQuantity(r.getClayQuantity() + clayIncrease);
            return r;
        }).collect(Collectors.toList());
        repository.saveAll(increased);
        repository.flush();
    }
}


