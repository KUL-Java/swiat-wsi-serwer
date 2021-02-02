package com.kuljava.swiatwsi;

import com.kuljava.swiatwsi.security.User;
import com.kuljava.swiatwsi.security.UserService;
import com.kuljava.swiatwsi.services.VillageService;
import org.springframework.context.ConfigurableApplicationContext;

public class DbFiller {

  static void fillDbWithData(ConfigurableApplicationContext run) {
    UserService userService = run.getBean(UserService.class);
    VillageService villageService = run.getBean(VillageService.class);
    createDefaultUser(userService);
    createDefaultVillage(villageService);
  }

  private static void createDefaultVillage(VillageService villageService) {
    villageService.saveVillageForCurrentUser("Wojciechow", "dandoo");
  }

  private static void createDefaultUser(UserService userService) {
    User user = new User();
    user.setUserName("dandoo");
    user.setPassword("password");
    user.setEmail("damian.michalak@gmail.com");
    userService.addWithDefaultRole(user);
  }
}
