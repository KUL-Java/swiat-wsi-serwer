package com.kuljava.swiatwsi.rawmaterials.repository;

import com.kuljava.swiatwsi.rawmaterials.entity.Iron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IronRepository extends JpaRepository<Iron, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Iron SET quantity = quantity + ?1")
    void increaseAll(Long incr);
}
