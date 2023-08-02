package com.templateproject.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.templateproject.api.entity.Player;
/**
 * *
 * @author smaile
 *
 */

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer>{
	public Player findByUsername(String username);
	public Player findByEmail(String email);
}
