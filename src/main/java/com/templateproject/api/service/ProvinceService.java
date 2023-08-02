package com.templateproject.api.service;

import org.springframework.stereotype.Service;

import com.templateproject.api.entity.Player;
import com.templateproject.api.entity.Province;
import com.templateproject.api.entity.Resource;
import com.templateproject.api.repository.PlayerRepository;
import com.templateproject.api.repository.ProvinceRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * *
 * @author smaile
 *
 */
@Service
public class ProvinceService {
    private final ProvinceRepository provinceRepository;
    private final PlayerRepository playerRepository;
    
    
    private final static int DEFAULT_POPULATION = 20;

    public ProvinceService(ProvinceRepository provinceRepository, PlayerRepository playerRepository) {
        this.provinceRepository = provinceRepository;
        this.playerRepository = playerRepository;
    }

    

    public void deleteById(int id) throws Exception {
        Optional<Province> province = provinceRepository.findById(id);
        if (province.isPresent()) {
            provinceRepository.delete(province.get());
        } else {
            throw new Exception("Province with ID " + id + " not found");
        }
    }

    public void deleteByName(String name) throws Exception {
        Optional<Province> province = provinceRepository.findByName(name);
        if (province.isPresent()) {
            provinceRepository.delete(province.get());
        } else {
            throw new Exception("Province with name '" + name + "' not found");
        }
    }

    public HashMap<String, Object> getById(int id) throws Exception {
        Optional<Province> optionalProvince = provinceRepository.findById(id);
        if (optionalProvince.isPresent()) {
            return convertToHashMap(optionalProvince.get());
        } else {
            throw new Exception("Province with ID " + id + " not found");
        }
    }

    public HashMap<String, Object> getByName(String name) throws Exception {
        Optional<Province> optionalProvince = provinceRepository.findByName(name);
        if (optionalProvince.isPresent()) {
            return convertToHashMap(optionalProvince.get());
        } else {
            throw new Exception("Province with name '" + name + "' not found");
        }
    }

    public int add(String name, int playerID) {
    	var player = playerRepository.findById(playerID).get();
        Province province = new Province();
        province.setName(name);
        province.setPopulation(DEFAULT_POPULATION);
        province.setPlayer(player);
        province = provinceRepository.save(province);
        return  province.getId();
    }

    public List<HashMap<String, Object>> getAll() {
        List<Province> provinces = provinceRepository.findAll();
        return provinces.stream().map(this::convertToHashMap).collect(Collectors.toList());
    }

    public void update(int id, String name, int population) throws Exception {
        Optional<Province> optionalProvince = provinceRepository.findById(id);
        if (optionalProvince.isPresent()) {
            Province province = optionalProvince.get();
            province.setName(name);
            province.setPopulation(population);
            provinceRepository.save(province);
        } else {
            throw new Exception("Province with ID " + id + " not found");
        }
    }

    private HashMap<String, Object> convertToHashMap(Province province) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", province.getId());
        map.put("name", province.getName());
        map.put("population", province.getPopulation());
        // Add other fields if needed
        return map;
    }
    
    public List<Province> getProvinceByPlayerId(Integer playerId ) {
    	List<Province> provinces = provinceRepository.getProvinceByPlayerId(playerId);
    	return provinces;
    }
   
}
