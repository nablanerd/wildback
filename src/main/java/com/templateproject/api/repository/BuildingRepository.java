package com.templateproject.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.templateproject.api.entity.Building;
/**
 * *
 * @author smaile
 *
 */
@Repository
public interface BuildingRepository extends JpaRepository<Building, Integer> {
	public Building findByName(String name);
}
