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
import com.templateproject.api.controller.payload.PlayerPayload;
import com.templateproject.api.entity.Player;
import com.templateproject.api.service.AuthService;
import com.templateproject.api.service.PlayerService;


/**
 * *
 * @author smaile
 *
 */

@RestController
public class PlayerController {
	 
	 private final PlayerService playerservice;
	 private final AuthService authService;
	 

	public PlayerController(PlayerService playerservice, AuthService authService) {
		this.playerservice = playerservice;
		this.authService = authService;
		
	}

	@PostMapping("/player")
	public ResponseEntity<Payload> addPlayer(@RequestBody PlayerPayload player) {
		var payload = new Payload();
		try {
			
//			playerservice.add(player.getUsername(), player.getPassword(), player.getEmail());
			authService.register(player.getUsername(), player.getEmail(), player.getPassword(), player.getCpassword());
			payload.setMessage(player.getEmail()+"created");
			return new ResponseEntity<>(payload, HttpStatus.CREATED);
		} catch (Exception e) {
			payload.setMessage(e.getMessage());
			return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	 @GetMapping("/player/{username}")
	    public ResponseEntity<Payload> getPlayerByUserName(@PathVariable String username) {
	        var payload = new Payload();
	        try {
	            var player = playerservice.getByUserName(username);
	            payload.setMessage("Get player by username '" + username +"'");
	            payload.setData(player);
	            return new ResponseEntity<>(payload, HttpStatus.OK);
	        } catch (Exception e) {
	            payload.setMessage(e.getMessage());
	            payload.setData(null);
	            return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	 
	 @GetMapping("/player/email/{email}")
	 public ResponseEntity<Payload> getPlayerByEmail(@PathVariable String email){
		 var payload = new Payload();
		 try {
			var player = playerservice.getByEmail(email);
			payload.setMessage("Get player by email '" + email +"'");
            payload.setData(player);
            return new ResponseEntity<>(payload, HttpStatus.OK);
		} catch (Exception e) {
			payload.setMessage(e.getMessage());
            payload.setData(null);
            return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
	 }
	 @DeleteMapping("/player/{username}")
	    public ResponseEntity<Payload> deleteUser(@PathVariable String username) {
	        var payload = new Payload();
	        try {
	        	playerservice.delete(username);
	            payload.setMessage("'" + username + "' deleted");
	            return new ResponseEntity<>(payload, HttpStatus.OK);
	        } catch (Exception e) { //TODO 4.x.x
	            payload.setMessage(e.getMessage());
	            return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	 
	 
	   @GetMapping("/players")
	    public ResponseEntity<Payload> getAllUser() {
	        var payload = new Payload();
	        try {
	            payload.setData(playerservice.getAll());
	            payload.setMessage("Get all user");
	            return new ResponseEntity<>(payload, HttpStatus.OK);
	        } catch (Exception e) {
	            payload.setMessage(e.getMessage());
	            payload.setData(null);
	            return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	   
	   @PutMapping("/player/{username}")
	    public ResponseEntity<Payload> updatePlayer(@PathVariable String username, @RequestBody Player player) {
	        var payload = new Payload();
	        try {
	            playerservice.update(username, player.getUsername(), player.getEmail(), player.getPassword());
	            payload.setMessage("User updated");
	            return new ResponseEntity<>(payload, HttpStatus.OK);
	        } catch (Exception e) { //TODO NOT FOUND
	            payload.setMessage(e.getMessage());
	            return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
}


