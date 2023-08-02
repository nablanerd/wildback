package com.templateproject.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.templateproject.api.controller.payload.Payload;
import com.templateproject.api.entity.Troop;
import com.templateproject.api.service.TroopService;
/**
 * *
 * @author smaile
 *
 */
@RestController
public class TroopController {

    private final TroopService troopService;

    public TroopController(TroopService troopService) {
        this.troopService = troopService;
    }

    @PostMapping("/troop")
    public ResponseEntity<Payload> addTroop(@RequestBody Troop troop) {
        var payload = new Payload();
        try {
            troopService.addTroop(troop.getName(), troop.getBattle());
            payload.setMessage("Troop created");
            return new ResponseEntity<>(payload, HttpStatus.CREATED);
        } catch (Exception e) {
            payload.setMessage(e.getMessage());
            return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/troop/{id}")
    public ResponseEntity<Payload> getTroopById(@PathVariable int id) {
        var payload = new Payload();
        try {
            var troop = troopService.getById(id);
            payload.setMessage("Get troop by ID: " + id);
            payload.setData(troop);
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (Exception e) {
            payload.setMessage(e.getMessage());
            payload.setData(null);
            return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/troop/name/{name}")
    public ResponseEntity<Payload> getTroopByName(@PathVariable String name) {
        var payload = new Payload();
        try {
            var troop = troopService.getByName(name);
            payload.setMessage("Get troop by name: " + name);
            payload.setData(troop);
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (Exception e) {
            payload.setMessage(e.getMessage());
            payload.setData(null);
            return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/troops")
    public ResponseEntity<Payload> getAllTroops() {
        var payload = new Payload();
        try {
            payload.setData(troopService.getAllTroops());
            payload.setMessage("Get all troops");
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (Exception e) {
            payload.setMessage(e.getMessage());
            payload.setData(null);
            return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/troop/{id}")
    public ResponseEntity<Payload> updateTroop(@PathVariable int id, @RequestBody Troop troop) {
        var payload = new Payload();
        try {
            troopService.updateTroop(id, troop.getName(), troop.getBattle());
            payload.setMessage("Troop updated");
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (Exception e) {
            payload.setMessage(e.getMessage());
            return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/troop/{id}")
    public ResponseEntity<Payload> deleteTroop(@PathVariable int id) {
        var payload = new Payload();
        try {
            troopService.deleteTroop(id);
            payload.setMessage("Troop deleted");
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (Exception e) {
            payload.setMessage(e.getMessage());
            return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
