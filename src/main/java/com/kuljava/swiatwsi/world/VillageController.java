package com.kuljava.swiatwsi.world;

import com.kuljava.swiatwsi.exceptions.VillageWithNameAlreadyExistsException;
import com.kuljava.swiatwsi.exceptions.VillagesAmountExceededException;
import com.kuljava.swiatwsi.services.VillageService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

  @PostMapping("/add")
  public Village addVillage(@RequestParam String name) {
    try {
      return villageService.saveVillage(name);

    } catch (VillagesAmountExceededException e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Village with such name already exists",e);

    } catch (VillageWithNameAlreadyExistsException e) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "The server is full, cannot create another village",e);
    }
  }
}
