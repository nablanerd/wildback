package com.templateproject.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
        b1.setStrength(5);
        b1.setWood(5);
        b1.setFood(5);
        b1.setWater(5);
        b1.setMoney(5);
        b1.setTroop(5);

        b1.setTroopWood(1);
        b1.setTroopFood(1);
        b1.setTroopWater(1);
        b1.setTroopMoney(1);

        buildingRepository.save(b1);

        var b2 = new Building();
        b2.setName("b2");
        b2.setType("t2");
        b2.setStrength(10);
        b2.setWood(10);
        b2.setFood(10);
        b2.setWater(10);
        b2.setMoney(10);
        b2.setTroop(10);

        b2.setTroopWood(2);
        b2.setTroopFood(2);
        b2.setTroopWater(2);
        b2.setTroopMoney(2);

        buildingRepository.save(b2);

        var b3 = new Building();
        b3.setName("b3");
        b3.setType("t3");
        b3.setStrength(15);
        b3.setWood(15);
        b3.setFood(15);
        b3.setWater(15);
        b3.setMoney(15);
        b3.setTroop(15);

        b3.setTroopWood(3);
        b3.setTroopFood(3);
        b3.setTroopWater(3);
        b3.setTroopMoney(3);

        buildingRepository.save(b3);

    }

    public List<Building> getAllBuildings()
    {
    return buildingRepository.findAll();

    }

    public List<Building> getAvaibleBuildings()
    {
        List<Building> newBuildings = new ArrayList<>();

       var buildings= buildingRepository.findAll();

       for (Building building : buildings) {

        if(Objects.isNull(building.getProvince()))
        {
            newBuildings.add(building);
        }



    }

return newBuildings;

    }

    public List<Building> getBuildingsFromProvence(Integer idProvince)
    {

    var province =  provinceservice.getOneProvinceById(idProvince);
        

    return province.getBuildings();
    
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


        public void updateTroop(int id, int troop) {
        Building building = buildingRepository.findById(id).get();
        if (building != null) {
            building.setTroop(troop);
            

            buildingRepository.save(building);
        }
    }


}
