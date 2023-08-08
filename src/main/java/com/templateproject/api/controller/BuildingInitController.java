package com.templateproject.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.templateproject.api.controller.payload.Payload;
import com.templateproject.api.entity.Building;
import com.templateproject.api.entity.BuildingType;
import com.templateproject.api.entity.TechnologyType;
import com.templateproject.api.repository.BuildingRepository;
import com.templateproject.api.service.BuildingService;
/**
 * *
 * @author smaile
 *
 */

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class BuildingInitController {
	
    private final BuildingService buildingService ;
    // @Autowired
    private BuildingRepository buildingRepository ;

    public BuildingInitController(BuildingService buildingService,
    		BuildingRepository buildingRepository) {

        this.buildingService = buildingService;
        this.buildingRepository = buildingRepository;
        
        Building b1 = new Building();
        b1.setName("Caserne de la garde pr\u00E9torienne");
        b1.setType("Caserne ");
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

        Building b2 = new Building();
        b2.setName("Caserne des cohortes urbaines");
        b2.setType("Caserne");
        b1.setStrength(10);
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

        Building b3 = new Building();
        b3.setName("Caserne des marins de Mis\u00E8ne");
        b3.setType("Caserne");
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

 
    
    @GetMapping("/buildinginit")
    public ResponseEntity<Payload> getbuildingtype()
    {

        var payload = new Payload();
        buildingService.init();
        payload.setMessage("buildings created");
        return new ResponseEntity<>(payload, HttpStatus.OK);
    }
}
