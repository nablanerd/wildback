package com.templateproject.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.templateproject.api.entity.Resource;
/**
 * *
 * @author smaile
 *
 */
@Repository
public interface ResourceRepository extends JpaRepository<Resource, Integer> {
     
    void deleteById(int id);
}
