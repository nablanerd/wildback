package com.templateproject.api.service;

import java.util.List;
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


    public void init()
    {

        var b1 = new Building();
        b1.setName("b1");
        b1.setType("t1");
        b1.setStrength(1);
        b1.setWood(1);
        b1.setFood(1);
        b1.setWater(1);
        b1.setMoney(1);
        b1.setTroop(1);

        buildingRepository.save(b1);

        var b2 = new Building();
        b2.setName("b2");
        b2.setType("t2");
        b1.setStrength(1);
        b1.setWood(2);
        b1.setFood(2);
        b1.setWater(2);
        b1.setMoney(2);
        b1.setTroop(2);

        buildingRepository.save(b2);

        var b3 = new Building();
        b3.setName("b3");
        b3.setType("t3");
        b1.setStrength(3);
        b1.setWood(3);
        b1.setFood(3);
        b1.setWater(3);
        b1.setMoney(3);
        b1.setTroop(3);
        buildingRepository.save(b3);

    }

    public List<Building> getAllBuildings()
    {
    return buildingRepository.findAll();

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
