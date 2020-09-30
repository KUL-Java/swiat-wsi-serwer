package com.kuljava.swiatwsi.rawmaterials;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RawMaterialsRepository extends JpaRepository<RawMaterials, Long> {

}
