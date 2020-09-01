package com.kuljava.swiatwsi.rawmaterials;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface RawMaterialsRepository extends JpaRepository<RawMaterials, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE RawMaterials r SET r.woodQuantity = r.woodQuantity + ?1, r.clayQuantity = r.clayQuantity + ?2, r.ironQuantity = r.ironQuantity + ?3")
    void increaseAll(Long woodIncr, Long clayIncr, Long ironIncr);

}
