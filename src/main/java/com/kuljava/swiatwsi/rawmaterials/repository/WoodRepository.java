package com.kuljava.swiatwsi.rawmaterials.repository;

import com.kuljava.swiatwsi.rawmaterials.entity.Wood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WoodRepository extends JpaRepository<Wood, Long> {
}
