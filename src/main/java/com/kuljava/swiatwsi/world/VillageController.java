package com.kuljava.swiatwsi.world;

import com.kuljava.swiatwsi.exceptions.VillagesAmountExceededException;
import com.kuljava.swiatwsi.services.VillageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/villages")
public class VillageController {

  private final VillageService villageService;

  @Autowired
  public VillageController(VillageService villageService) {
    this.villageService = villageService;
  }

  @GetMapping
  public ResponseEntity<Village> getVillageForUser() {
    String userName = SecurityContextHolder.getContext().getAuthentication().getName();
    return villageService
        .findVillageForUser(userName)
        .map(ResponseEntity::ok)
        .orElseGet(ResponseEntity.notFound()::build);
  }

  @PostMapping
  public HttpStatus addVillage(@RequestParam String name) {
    try {
      String userName = SecurityContextHolder.getContext().getAuthentication().getName();
      villageService.saveVillageForCurrentUser(name, userName);
      return HttpStatus.ACCEPTED;
    } catch (VillagesAmountExceededException e) {
      throw new ResponseStatusException(
          HttpStatus.SERVICE_UNAVAILABLE, "The server is full, cannot create another village", e);
    }
  }
}
