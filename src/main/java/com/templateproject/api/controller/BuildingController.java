package com.templateproject.api.controller;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.templateproject.api.controller.payload.BuildingPayload;
import com.templateproject.api.controller.payload.BuildingTroop;
import com.templateproject.api.controller.payload.Payload;
import com.templateproject.api.entity.Building;
import com.templateproject.api.entity.BuildingType;
import com.templateproject.api.entity.Province;
import com.templateproject.api.entity.TechnologyType;
import com.templateproject.api.repository.BuildingRepository;
import com.templateproject.api.repository.PlayerRepository;
import com.templateproject.api.repository.ProvinceRepository;
import com.templateproject.api.service.BuildingService;
import com.templateproject.api.service.PlayerService;
import com.templateproject.api.service.ProvinceService;

import com.templateproject.api.entity.Player;

/**
 * *
 * 
 * @author smaile
 *
 */

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class BuildingController {

    private final BuildingService buildingService;
    private final ProvinceService provinceservice;
    private final ProvinceRepository provinceRepository;
    private final PlayerService playerService;
    private final PlayerRepository playerRepository;
    private final BuildingRepository buildingRepository;

    public BuildingController(BuildingService buildingService,
            ProvinceService provinceservice,
            ProvinceRepository provinceRepository,
            PlayerService playerService,
            PlayerRepository playerRepository,
            BuildingRepository buildingRepository) {
        this.buildingService = buildingService;
        this.provinceservice = provinceservice;
        this.provinceRepository = provinceRepository;
        this.playerService = playerService;
        this.playerRepository = playerRepository;
        this.buildingRepository = buildingRepository;
    }

    @PutMapping("/updatebuildingwithprovince/{buildingId}/{provinceId}")
    public ResponseEntity<Payload> updateBuildingWithProvince(@PathVariable int buildingId,
            @PathVariable int provinceId) {

        Payload payload = new Payload();
        var data = new HashMap<String, String>();

        try {

            Province province = provinceRepository.findById(provinceId).get();
            Building building = buildingService.getById(buildingId);
            Player player = playerRepository.findById(province.getPlayer().getId()).get();
            if (player.getWood() > building.getWood() &&
                    player.getWater() > building.getWater() &&
                    player.getFood() > building.getFood() &&
                    player.getMoney() > building.getMoney()) {

                var newWood = player.getWood() - building.getWood() < 0 ? 0 : player.getWood() - building.getWood();        
                player.setWood(newWood);

                var newWater = player.getWater() - building.getWater() < 0? 0 : player.getWater() - building.getWater();
                player.setWater(newWater);

                var newFood = player.getFood() - building.getFood() <0 ?0: player.getFood() - building.getFood();
                player.setFood(newFood);

                var newMoney = player.getMoney() - building.getMoney() <0 ?0 : player.getMoney() - building.getMoney();
                player.setMoney(newMoney);

                building.setProvince(province);
                buildingRepository.save(building);
                playerRepository.save(player);
                provinceRepository.save(province);

                payload.setMessage("building mis a jour avec succes");

                data.put("type", "ok");
                data.put("message", "building mis a jour avec succes");

                payload.setData(data);

                return new ResponseEntity<>(payload, HttpStatus.OK);
            }

            payload.setMessage("pas assez de ressource pour construire building");

            data.put("type", "ko");
            data.put("message", "pas assez de ressource pour construire building");

            payload.setData(data);

            return new ResponseEntity<>(payload, HttpStatus.OK);

        } catch (Exception e) {
            payload.setMessage(e.getMessage());
            return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/building")
    public ResponseEntity<Payload> addBuilding(@RequestBody BuildingPayload building) {
        var payload = new Payload();
        try {
            TechnologyType technology = BuildingService.getTechnologyTypeFromString(building.getTechnology());
            BuildingType buildingType = BuildingService.getBuildingTypeFromString(building.getBuildingtype());

            if (technology == null || buildingType == null) {
                payload.setMessage("type technolofy ou building invalide");
                return new ResponseEntity<>(payload, HttpStatus.BAD_REQUEST);
            }

            buildingService.add(building.getName(), building.getProvinceID(), technology, buildingType);
            payload.setMessage("Building created with success !!");
            return new ResponseEntity<>(payload, HttpStatus.CREATED);
        } catch (Exception e) {
            payload.setMessage(e.getMessage());
            return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/building/{id}")
    public ResponseEntity<Payload> getBuildingById(@PathVariable int id) {
        var payload = new Payload();
        try {
            Building building = buildingService.getById(id);
            payload.setData(building);
            payload.setMessage("Get building by id: " + id);
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (Exception e) {
            payload.setMessage(e.getMessage());
            return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Payload> getBuildingByName(@PathVariable String name) {
        var payload = new Payload();
        try {
            Building building = buildingService.getByName(name);
            payload.setData(building);
            payload.setMessage("Get building by name: " + name);
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (Exception e) {
            payload.setMessage(e.getMessage());
            return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/building/{id}")
    public ResponseEntity<Payload> updateBuilding(@PathVariable int id, @RequestBody Building building) {
        var payload = new Payload();
        try {
            buildingService.update(id, building.getName(), building.getProvince(), building.getTechnology(),
                    building.getBuildingtype());
            payload.setMessage("Building updated");
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (Exception e) {
            payload.setMessage(e.getMessage());
            return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Payload> deleteBuilding(@PathVariable int id) {
        var payload = new Payload();
        try {
            buildingService.delete(id);
            payload.setMessage("Building with id: " + id + " deleted");
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (Exception e) {
            payload.setMessage(e.getMessage());
            return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/name/{buildingname}")
    public ResponseEntity<Payload> deleteBuildingByName(@PathVariable String buildingname) {
        var payload = new Payload();
        try {
            buildingService.deleteByName(buildingname);
            payload.setMessage("Building with name: " + buildingname + " deleted");
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (Exception e) {
            payload.setMessage(e.getMessage());
            return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/buildingsall")
    public ResponseEntity<Payload> getAllBuildings() {
        var payload = new Payload();

        try {
            payload.setMessage("getAllBuildings");
            payload.setData(buildingService.getAllBuildings());

            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (Exception e) {
            payload.setMessage(e.getMessage());
            return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/buildingsavaible")
    public ResponseEntity<Payload> getAvaibleBuildings() {
        var payload = new Payload();

        try {
            payload.setMessage("getAvaibleBuildings");
            payload.setData(buildingService.getAvaibleBuildings());

            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (Exception e) {
            payload.setMessage(e.getMessage());
            return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getbuildingsfromprovence/{idprovince}")
    public ResponseEntity<Payload> getBuildingsFromProvence(@PathVariable Integer idprovince) {
        var payload = new Payload();

        try {
            payload.setMessage("getAvaibleBuildings");
            payload.setData(buildingService.getBuildingsFromProvence(idprovince));

            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (Exception e) {
            payload.setMessage(e.getMessage());
            return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/buildingtroop/{id}")
    public ResponseEntity<Payload> updateBuildingTroop(@PathVariable int id, @RequestBody BuildingTroop buildingTroop) {
        var payload = new Payload();
        var data = new HashMap<String, String>();

        try {

            Building building = buildingService.getById(id);

            var province = building.getProvince();

            var player = province.getPlayer();

            var priceTroopWood = building.getTroopWood() * building.getTroop();
            var priceTroopWater = building.getTroopWater() * buildingTroop.getTroop();
            var priceTroopFood = building.getTroopFood() * buildingTroop.getTroop();
            var priceTroopMoeny = building.getTroopMoney() * buildingTroop.getTroop();

            if (player.getWood() > priceTroopWood &&
                    player.getWater() >  priceTroopWater &&
                    player.getFood() > priceTroopFood &&
                    player.getMoney() > priceTroopMoeny) {

                buildingService.updateTroop(id, buildingTroop.getTroop());

                var newWood = player.getWood() - priceTroopWood < 0 ? 0 : player.getWood() - priceTroopWood;
                player.setWood(newWood);

                var newWater = player.getWater() - building.getWater() < 0 ? 0 : player.getWater() - building.getWater();
                player.setWater(newWater);

                var newFood = player.getFood() - building.getFood() < 0 ? 0 : player.getFood() - building.getFood();
                player.setFood(newFood);

                var newMoney = player.getMoney() - building.getMoney() < 0 ? 0:player.getMoney() - building.getMoney();

                player.setMoney(newMoney);

                playerRepository.save(player);




                payload.setMessage("Building Troop updated");

                data.put("type", "ok");
                data.put("message", "Building Troop updated");

                payload.setData(data);

                return new ResponseEntity<>(payload, HttpStatus.OK);

            }

            payload.setMessage("buildingtroop pas assez de ressource pour recruter");
            data.put("type", "ko");
            data.put("message", "pas assez de ressource pour recruter");

            payload.setData(data);

            return new ResponseEntity<>(payload, HttpStatus.OK);

        } catch (Exception e) {
            payload.setMessage(e.getMessage());
            return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
