package com.kuljava.swiatwsi.services;

import com.kuljava.swiatwsi.rawmaterials.RawMaterials;
import com.kuljava.swiatwsi.rawmaterials.RawMaterialsDTO;
import com.kuljava.swiatwsi.rawmaterials.RawMaterialsRepository;
import com.kuljava.swiatwsi.world.Village;
import com.kuljava.swiatwsi.world.VillageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RawMaterialsService {

    private final VillageRepository villageRepository;
    private final RawMaterialsRepository rawMaterialsRepository;
    private final RawMaterialsDTO dto;

    public Optional<RawMaterials> getRawMaterialsByVillageId(Long id) {
        return villageRepository.findById(id).map(Village::getRawMaterials);
    }

    public void createRawMaterialsForVillage(Optional<Village> villageOptional) {
        villageOptional.ifPresent(
                this::saveVillageWithMaterials
        );
    }

    private void saveVillageWithMaterials(Village village) {
        RawMaterials rawMaterials = new RawMaterials(dto.getWoodInitialValue(), dto.getClayInitialValue(), dto.getIronInitialValue());
        village.setRawMaterials(rawMaterials);
        villageRepository.saveAndFlush(village);
    }
}