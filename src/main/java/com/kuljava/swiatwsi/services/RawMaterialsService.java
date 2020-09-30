package com.kuljava.swiatwsi.services;

import com.kuljava.swiatwsi.rawmaterials.RawMaterials;
import com.kuljava.swiatwsi.rawmaterials.RawMaterialsRepository;
import com.kuljava.swiatwsi.world.Village;
import com.kuljava.swiatwsi.world.VillageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RawMaterialsService {

    VillageRepository villageRepository;
    RawMaterialsRepository rawMaterialsRepository;

    public Optional<RawMaterials> getRawMaterialsByVillageId(Long id) {
        return villageRepository.findById(id).map(Village::getRawMaterials);
    }

    public void createRawMaterialsForVillage(Optional<Village> villageOptional) {
        villageOptional.ifPresent(
                this::saveVillageWithMaterials
        );
    }

    private void saveVillageWithMaterials(Village village) {
        RawMaterials rawMaterials = new RawMaterials(10L, 10L, 10L);
        village.setRawMaterials(rawMaterials);
        villageRepository.saveAndFlush(village);
    }
}