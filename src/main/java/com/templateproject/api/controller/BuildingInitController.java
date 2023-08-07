package com.templateproject.api.controller;

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
import com.templateproject.api.service.BuildingService;
/**
 * *
 * @author smaile
 *
 */

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class BuildingInitController {

    private final BuildingService buildingService;

    public BuildingInitController(BuildingService buildingService) {

        this.buildingService = buildingService;

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
