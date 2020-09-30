package com.kuljava.swiatwsi.rawmaterials;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class RawMaterialsScheduler {

    private final RawMaterialsRepository repository;
    private final RawMaterialsDTO dto;

    @Scheduled(fixedRateString = "${rawmaterials.scheduler.fixedRate}")
    public void increaseQuantitiesByDefaultValue() {
        List<RawMaterials> rawMaterialsList = repository.findAll();
        rawMaterialsList.forEach(r -> {
            r.setIronQuantity(r.getIronQuantity() + dto.getIronIncrease());
            r.setWoodQuantity(r.getWoodQuantity() + dto.getWoodIncrease());
            r.setClayQuantity(r.getClayQuantity() + dto.getClayIncrease());
        });
        repository.saveAll(rawMaterialsList);
    }
}


