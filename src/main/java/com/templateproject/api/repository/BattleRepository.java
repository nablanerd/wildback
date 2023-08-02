package com.templateproject.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.templateproject.api.entity.Battle;
/**
 * *
 * @author smaile
 *
 */
@Repository
public interface BattleRepository extends JpaRepository<Battle, Integer> {
	public Battle findByName(String name);
}
