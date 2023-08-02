package com.templateproject.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.templateproject.api.controller.payload.Payload;
import com.templateproject.api.entity.Battle;
import com.templateproject.api.service.BattleService;

@RestController
public class BattleController {

    private final BattleService battleService;

    public BattleController(BattleService battleService) {
        this.battleService = battleService;
    }

    @PostMapping("/battle")
    public ResponseEntity<Payload> addBattle(@RequestBody Battle battle) {
        var payload = new Payload();
        try {
            battleService.add(battle);
            payload.setMessage(battle.getName() + " created");
            return new ResponseEntity<>(payload, HttpStatus.CREATED);
        } catch (Exception e) {
            payload.setMessage(e.getMessage());
            return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/battle/{id}")
    public ResponseEntity<Payload> getBattleById(@PathVariable int id) {
        var payload = new Payload();
        try {
            var battle = battleService.getById(id);
            payload.setMessage("Get battle by ID: " + id);
            payload.setData(battle);
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (Exception e) {
            payload.setMessage(e.getMessage());
            payload.setData(null);
            return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/battles")
    public ResponseEntity<Payload> getAllBattles() {
        var payload = new Payload();
        try {
            payload.setData(battleService.getAll());
            payload.setMessage("Get all battles");
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (Exception e) {
            payload.setMessage(e.getMessage());
            payload.setData(null);
            return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/battle/{id}")
    public ResponseEntity<Payload> updateBattle(@PathVariable int id, @RequestBody Battle battle) {
        var payload = new Payload();
        try {
            battleService.update(id, battle.getName());
            payload.setMessage("Battle updated");
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (Exception e) {
            payload.setMessage(e.getMessage());
            return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/battle/{id}")
    public ResponseEntity<Payload> deleteBattle(@PathVariable int id) {
        var payload = new Payload();
        try {
            battleService.delete(id);
            payload.setMessage("Battle with ID: " + id + " deleted");
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (Exception e) {
            payload.setMessage(e.getMessage());
            return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //  récupérer une bataille par son nom
    @GetMapping("/battle/{name}")
    public ResponseEntity<Payload> getBattleByName(@PathVariable String name) {
        var payload = new Payload();
        try {
            Battle battle = battleService.getByName(name);
            payload.setData(battle);
            payload.setMessage("Get battle by name: " + name);
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (Exception e) {
            payload.setMessage(e.getMessage());
            return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/name/{battlename}")
    public ResponseEntity<Payload> deleteBattleByName(@PathVariable String battlename) {
        var payload = new Payload();
        try {
            battleService.deleteByName(battlename);
            payload.setMessage("Battle with name: " + battlename + " deleted");
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (Exception e) {
            payload.setMessage(e.getMessage());
            return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
