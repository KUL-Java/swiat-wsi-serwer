package com.kuljava.swiatwsi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/villages")
public class VillageController {

    @Autowired
    private
    VillageRepository villageRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAll(){
        StringBuilder stringBuilder = new StringBuilder();
        villageRepository.findAll().forEach(x -> stringBuilder.append(x).append("\n"));
        return stringBuilder.toString();
    }
}
