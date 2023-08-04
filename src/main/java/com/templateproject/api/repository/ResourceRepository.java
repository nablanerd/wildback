package com.templateproject.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.templateproject.api.entity.Province;
import com.templateproject.api.entity.Resource;
/**
 * *
 * @author smaile
 *
 */
@Repository
public interface ResourceRepository extends JpaRepository<Resource, Integer> {
     
    void deleteById(Integer id);
//    public List<Resource> findByProvince(Integer ProvinceID );
    public List<Resource> findByProvince(Province province );
//    public List<Resource> findByProvinceID(Integer ProvinceID );
}
