package com.kuljava.swiatwsi.services;

import com.kuljava.swiatwsi.rawmaterials.RawMaterials;
import com.kuljava.swiatwsi.rawmaterials.RawMaterialsRepository;
import com.kuljava.swiatwsi.world.Village;
import com.kuljava.swiatwsi.world.VillageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RawMaterialsService {
  @Autowired VillageRepository villageRepository;
  @Autowired RawMaterialsRepository rawMaterialsRepository;

  public Optional<RawMaterials> getRawMaterialsByVillageId(Long id) {
    Optional<Village> village = villageRepository.findById(id);
    return village.isEmpty()
        ? Optional.empty()
        : Optional.ofNullable(village.get().getRawMaterials());
  }

  public void createRawMaterialsForVillage(Village village) {
    if (village != null) {
      RawMaterials rawMaterials = new RawMaterials(10L, 10L, 10L);
      rawMaterialsRepository.saveAndFlush(rawMaterials);
      village.setRawMaterials(rawMaterials);
      villageRepository.saveAndFlush(village);
    }
  }
}
