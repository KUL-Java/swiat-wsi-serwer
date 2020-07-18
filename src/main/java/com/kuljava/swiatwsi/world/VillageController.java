package com.kuljava.swiatwsi.world;

import com.kuljava.swiatwsi.exceptions.VillagesAmountExceededException;
import com.kuljava.swiatwsi.services.VillageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/villages")
public class VillageController {

  private VillageService villageService;

  @Autowired
  public VillageController(VillageService villageService) {
    this.villageService = villageService;
  }

  @GetMapping()
  public List<Village> getAll() {
    return villageService.findAllVillages();
  }

  @PostMapping()
  public ResponseEntity<Village> addVillage(@RequestParam String name) {
    try {
      final Optional<Village> village = villageService.saveVillage(name);
      return village
          .map(value -> new ResponseEntity<>(value, HttpStatus.CREATED))
          .orElseGet(() -> new ResponseEntity<>(HttpStatus.CONFLICT));
    } catch (VillagesAmountExceededException e) {
      throw new ResponseStatusException(
          HttpStatus.SERVICE_UNAVAILABLE, "The server is full, cannot create another village", e);
    }
  }
}
