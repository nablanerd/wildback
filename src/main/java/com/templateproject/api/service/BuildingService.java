package com.templateproject.api.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.templateproject.api.entity.Building;
import com.templateproject.api.entity.BuildingType;
import com.templateproject.api.entity.Province;
import com.templateproject.api.entity.TechnologyType;
import com.templateproject.api.repository.BuildingRepository;
import com.templateproject.api.repository.ProvinceRepository;
/**
 * *
 * @author smaile
 *
 */
@Service
public class BuildingService {
    private final BuildingRepository buildingRepository;
    private final ProvinceRepository provinceRepository;
    private final ProvinceService provinceservice;

    public BuildingService(BuildingRepository buildingRepository, 
    		ProvinceRepository provinceRepository,
    		ProvinceService provinceservice) {
        this.buildingRepository = buildingRepository;
        this.provinceRepository = provinceRepository;
        this.provinceservice = provinceservice;
    }


    public Building getById(int id) {
        return buildingRepository.findById(id).orElse(null);
    }

    
    public Building getByName(String name) {
        return buildingRepository.findByName(name);
    }


    public void add(String name, int provinceID, TechnologyType technology, BuildingType buildingtype) {
    	Optional<Province> optionalProvince = provinceRepository.findById(provinceID);
        Building building = new Building();
        building.setName(name);
        building.setProvince(optionalProvince.get());
        building.setTechnology(technology);
        building.setBuildingtype(buildingtype);
        buildingRepository.save(building);
    }

  
    public void update(int id, String name, Province province, TechnologyType technology, BuildingType buildingtype) {
        Building building = buildingRepository.findById(id).orElse(null);
        if (building != null) {
            building.setName(name);
            building.setProvince(province);
            building.setTechnology(technology);
            building.setBuildingtype(buildingtype);
            buildingRepository.save(building);
        }
    }

    // supprimer  ID
    public void delete(int id) {
        buildingRepository.deleteById(id);
    }
    
    
    public void deleteByName(String name) {
        Building building = buildingRepository.findByName(name);
        if (building != null) {
            buildingRepository.deleteById(building.getId());
        }
    }
    
    
    public static TechnologyType getTechnologyTypeFromString(String technologyString) {
        try {
            return TechnologyType.valueOf(technologyString.toLowerCase());
        } catch (IllegalArgumentException e) {
            // TODO
            return null;
        }
    }

    public static BuildingType getBuildingTypeFromString(String buildingTypeString) {
        try {
            return BuildingType.valueOf(buildingTypeString.toLowerCase());
        } catch (IllegalArgumentException e) {
            // TODO chaine invalid
        	return null ;
        }
    }
}
