package com.kuljava.swiatwsi.services;

import com.kuljava.swiatwsi.exceptions.UserNotFoundException;
import com.kuljava.swiatwsi.security.User;
import com.kuljava.swiatwsi.security.UserService;
import com.kuljava.swiatwsi.world.Village;
import com.kuljava.swiatwsi.world.VillageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VillageService {

  private final VillageRepository villageRepository;
  private final FreeCoordinatesFinderService freeCoordinatesFindersService;
  private final UserService userService;

  public void saveVillageForCurrentUser(String name, String userName) {
        userService
            .findUserByUsername(userName)
            .map(user -> createVillageWithName(name, user))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .ifPresent(villageRepository::save);
  }

  private Optional<Village> createVillageWithName(String name, User user) {
    if (villageRepository.findByName(name).isPresent()) {
      return Optional.empty();
    }
    return Optional.ofNullable(freeCoordinatesFindersService.createNextVillage(name, user));
  }

  public Optional<Village> findVillageForUser(String userName) {
    Optional<User> user = userService.findUserByUsername(userName);
    return user.map(villageRepository::findByUser).orElseThrow(UserNotFoundException::new);
  }
}
