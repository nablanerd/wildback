package com.templateproject.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.templateproject.api.entity.Troop;
/**
 * *
 * @author smaile
 *
 */
@Repository
public interface TroopRepository extends JpaRepository<Troop, Integer> {
    Troop findByName(String name);
}
