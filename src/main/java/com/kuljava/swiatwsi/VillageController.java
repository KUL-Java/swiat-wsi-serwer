package com.kuljava.swiatwsi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/villages")
public class VillageController {

  @Autowired private VillageRepository villageRepository;

  @GetMapping("/all")
  public List<Village> getAll() {
    return villageRepository.findAll();
  }
}
