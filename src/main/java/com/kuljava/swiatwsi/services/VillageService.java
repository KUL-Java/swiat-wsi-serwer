package com.kuljava.swiatwsi.services;

import com.kuljava.swiatwsi.security.SecurityUtils;
import com.kuljava.swiatwsi.exceptions.VillageNotFoundException;
import com.kuljava.swiatwsi.security.User;
import com.kuljava.swiatwsi.security.UserService;
import com.kuljava.swiatwsi.world.Village;
import com.kuljava.swiatwsi.world.VillageRepository;
import com.kuljava.swiatwsi.world.VillageView;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VillageService {

  private final VillageRepository villageRepository;
  private final FreeCoordinatesFinderService freeCoordinatesFindersService;
  private final UserService userService;
  private final Converter<Village, VillageView> villageToViewConverter;

  public Optional<VillageView> saveVillage(String name) {
    Optional<Village> createdVillage = createVillageWithName(name);
    createdVillage.ifPresent(
        v -> {
          v.setOwner(findLoggedInUser());
          villageRepository.save(v);
        });

    return createdVillage.map(villageToViewConverter::convert);
  }

  public VillageView findCurrentUserVillage() {
    return villageRepository
            .findUserVillage(SecurityUtils.getLoggedInUser().getName())
            .map(villageToViewConverter::convert)
            .orElseThrow(
                    () -> new VillageNotFoundException("Cannot get village, you first need to create it!"));
  }

  public List<VillageView> findAllVillages() {
    return villageRepository.findAll().stream()
            .map(villageToViewConverter::convert)
            .collect(Collectors.toList());
  }

  private Optional<Village> createVillageWithName(String name) {
    if (villageRepository.findByName(name).isPresent()) {
      return Optional.empty();
    }
    return Optional.ofNullable(freeCoordinatesFindersService.createNextVillage(name));
  }

  private User findLoggedInUser() {
    return userService
        .findUserByName(SecurityUtils.getLoggedInUser().getName())
        .orElseThrow(() -> new RuntimeException("You are not logged in!"));
  }


}
