package com.kuljava.swiatwsi.world;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VillageRepository extends JpaRepository<Village, Long> {
  Optional<Village> findByName(String name);

  @Query("SELECT t from Village t WHERE t.owner.userName = ?1")
  Optional<Village> findUserVillage(String username);
}
