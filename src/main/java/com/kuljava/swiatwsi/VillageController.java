package com.kuljava.swiatwsi;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
