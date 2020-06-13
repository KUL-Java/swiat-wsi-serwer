package com.kuljava.swiatwsi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VillageRepository extends JpaRepository<Village, Long> {
  Optional<Village> findByName(String name);

  Optional<Village> findByXAndY(int x, int y);

}
