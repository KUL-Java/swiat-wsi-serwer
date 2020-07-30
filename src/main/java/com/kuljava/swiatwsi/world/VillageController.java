package com.kuljava.swiatwsi.world;

import com.kuljava.swiatwsi.exceptions.VillageNotFoundException;
import com.kuljava.swiatwsi.exceptions.VillagesAmountExceededException;
import com.kuljava.swiatwsi.services.VillageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/villages")
@RequiredArgsConstructor
public class VillageController {

  private final VillageService villageService;

  @GetMapping("/")
  public List<VillageView> getAll() {
    return villageService.findAllVillages();
  }

  @GetMapping("/my")
  public VillageView myVillage(){
    return villageService.findCurrentUserVillage();
  }

  @PostMapping("/create")
  public ResponseEntity<VillageView> addVillage(@RequestParam String name) {
      final Optional<VillageView> village = villageService.saveVillage(name);
      return village
          .map(value -> new ResponseEntity<>(value, HttpStatus.CREATED))
          .orElseGet(() -> new ResponseEntity<>(HttpStatus.CONFLICT));
  }

  @ExceptionHandler(VillagesAmountExceededException.class)
  public ResponseEntity<String> handleVillageAmountExceeded(Exception thrown){
    return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(thrown.getMessage());
  }

  @ExceptionHandler(VillageNotFoundException.class)
  public ResponseEntity<String> handleVillageNotFound(Exception thrown){
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(thrown.getMessage());
  }

}
