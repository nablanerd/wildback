package com.templateproject.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.templateproject.api.entity.Province;
/**
 * *
 * @author smaile
 *
 */
@Repository
public interface ProvinceRepository extends JpaRepository<Province, Integer> {
	//    Province findByName(String name);
   
    void deleteById(int id);
    void deleteByName(String name);
    Optional<Province> findByName(String name);
    public List<Province> getProvinceByPlayerId(int playerID );
}
