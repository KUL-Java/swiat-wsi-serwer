package com.kuljava.swiatwsi.world;

import com.kuljava.swiatwsi.services.VillageService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/villages")
public class VillageController {

  private VillageService villageService;

  @Autowired
  public VillageController(VillageService villageService) {
    this.villageService = villageService;
  }

  @GetMapping("/all")
  public List<Village> getAll() {
    return villageService.findAllVillages();
  }

}
