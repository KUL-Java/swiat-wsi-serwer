package com.kuljava.swiatwsi.rawmaterials.repository;

import com.kuljava.swiatwsi.rawmaterials.entity.Clay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClayRepository extends JpaRepository<Clay, Long> {
}
