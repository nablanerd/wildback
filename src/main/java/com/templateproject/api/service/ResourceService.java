package com.templateproject.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.templateproject.api.entity.Province;
import com.templateproject.api.entity.Resource;
import com.templateproject.api.repository.ProvinceRepository;
import com.templateproject.api.repository.ResourceRepository;
/**
 * *
 * @author smaile
 *
 */
@Service
public class ResourceService {
    private final ResourceRepository resourceRepository;
    private final ProvinceRepository provinceRepository;
    
    private final static int DEFAULT_WOOD = 50;
    private final static int DEFAULT_WATER = 100;
    private final static int DEFAULT_FOOD = 50;

    public ResourceService(ResourceRepository resourceRepository, ProvinceRepository provinceRepository) {
        this.resourceRepository = resourceRepository;
        this.provinceRepository = provinceRepository;
    }

    public void add(int provinceID, int wood, int water, int food) {
        // Vérifier les paramètres si nécessaire
    	Optional<Province> province = provinceRepository.findById(provinceID);
        var resource = new Resource();        
        resource.setProvince(province.get());
        resource.setWood(wood);
        resource.setWater(water);
        resource.setFood(food);
        resourceRepository.save(resource);
    }
    
    public void add(int provinceID) {
    	var province = provinceRepository.findById(provinceID).get();
    	var resource = new Resource(DEFAULT_WOOD, DEFAULT_WATER, DEFAULT_FOOD);
    	resource.setProvince(province);
    	resourceRepository.save(resource);
    }

    public void deleteById(int id) {
        resourceRepository.deleteById(id);
    }

    public List<Resource> getAll() {
        return resourceRepository.findAll();
    }

    public Resource getById(int id) {
        return resourceRepository.findById(id).orElse(null);
    }


    public void update(int id, Integer wood, Integer water, Integer food) throws Exception {
        var resource = resourceRepository.findById(id).orElse(null);
        if (resource == null) {
        	throw new Exception("Resource_id '" + id + "'  NOT FOUND");
        }
        if (wood != null) {
        	resource.setWood(wood);
        }
        if (water != null) {
        	resource.setWater(water);
        }
        if (food != null) {
        	resource.setFood(food);
        }
        resourceRepository.save(resource);

    }
}
