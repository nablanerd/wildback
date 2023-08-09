package com.templateproject.api.controller;

import java.util.HashMap;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.templateproject.api.controller.payload.Payload;
import com.templateproject.api.entity.Battle;
import com.templateproject.api.entity.Province;
import com.templateproject.api.repository.PlayerRepository;
import com.templateproject.api.service.AuthService;
import com.templateproject.api.service.BattleService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class BattleController {

    private final BattleService battleService;

    	private final PlayerRepository playerrepository;


        private final AuthService authService;

    public BattleController(AuthService authService,BattleService battleService, PlayerRepository playerrepository) {
        this.battleService = battleService;
        this.authService = authService;
        this.playerrepository = playerrepository;

    }
    
    
    @GetMapping("/points")
    public ResponseEntity<Payload> points(@RequestHeader HttpHeaders headers) {


            var payload = new Payload();
            var data = new HashMap<String, Object>();

            try {
                        String token = headers.get("x-token").get(0);


                var currentPlayer = authService.getPlayerByToken(token);

                var currentPlayerPoints = battleService.getPoints(currentPlayer.getId());
                
                	payload.setMessage("You are The winner ");

                    data.put("points", currentPlayerPoints);

                    payload.setData(data);

			    return new ResponseEntity<>(payload, HttpStatus.OK);
            	} catch (Exception e) {
                payload.setMessage(e.getMessage());
                payload.setData(null);
                return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
            }
  }

    
    @GetMapping("/attack/{adversePlayerId}")
    public ResponseEntity<Payload> attack(@RequestHeader HttpHeaders headers, @PathVariable int adversePlayerId) {

        String token = headers.get("x-token").get(0);

            var payload = new Payload();
            var data = new HashMap<String, Object>();

            try {
                var currentPlayer = authService.getPlayerByToken(token);
                var adversePlayer = playerrepository.findById(adversePlayerId).get();

                var currentPlayerPoints = battleService.getPoints(currentPlayer.getId());
                var adversePlayerPoints = battleService.getPoints(adversePlayerId);

			    if(currentPlayerPoints > adversePlayerPoints)
			    {

                 battleService.transfertProvince(currentPlayer, adversePlayer);

			    	payload.setMessage("You are The winner ");

                    data.put("iswinner", true);

                    payload.setData(data);


			    }
			    else
			    {
                    battleService.transfertProvince(adversePlayer, currentPlayer);

			    	payload.setMessage("You are Loser ");

                    data.put("iswinner", false);
                    payload.setData(data);

			    }
                
			    return new ResponseEntity<>(payload, HttpStatus.OK);
            	} catch (Exception e) {
                payload.setMessage(e.getMessage());
                payload.setData(null);
                return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
            }
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


        @GetMapping("/fakebattle")
            public ResponseEntity<Payload> fakebattle() 
{
            var payload = new Payload();

            payload.setMessage("the winner is "+1);
            var data = new HashMap<String, Object>();

            data.put("winnerId", 1);
            data.put("loserId", 2);

            payload.setData(data);

    return new ResponseEntity<>(payload, HttpStatus.OK);


}


@GetMapping("/ranking")
  public ResponseEntity<Payload> getRanking() {
      var payload = new Payload();
      try {
          
        var players = battleService.getRanking();

        payload.setData(players);


          payload.setMessage("Get Ranking");

          return new ResponseEntity<>(payload, HttpStatus.OK);
      } catch (Exception e) {
          payload.setMessage(e.getMessage());
          payload.setData(null);
          return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }





}
