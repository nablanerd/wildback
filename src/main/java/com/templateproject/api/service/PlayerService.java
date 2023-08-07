package com.templateproject.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;


import com.templateproject.api.entity.Battle;
import com.templateproject.api.entity.Player;
import com.templateproject.api.entity.Province;
import com.templateproject.api.repository.PlayerRepository;
/**
 * *
 * @author smaile
 *
 */

@Service
public class PlayerService {
	private final PlayerRepository playerrepository;

	public PlayerService(PlayerRepository playerrepository) {
		this.playerrepository = playerrepository;
	}
	
	public void add(String username, String password, String email) {
		//tOTO check param 
		var player = new Player(username, password, email);
		playerrepository.save(player);		
	}
	public void add2(String username, String password, String email ) {
		//tOTO check param 
		var player = new Player(username, password, email, new ArrayList<Province>(), new Battle());
		playerrepository.save(player);
		System.out.println(player.toString());
		
	}
	
	
	 public void delete(String username) {
	        var player = playerrepository.findByUsername(username);
	        System.out.println(player.getEmail());
	        playerrepository.deleteById(player.getId());
	    }
	 
	 
	 public HashMap<String, String> getByUserName(String username) {
	        //TODO CHECK PARAMS
	        var player = new HashMap<String, String>();

	        var userEntity = playerrepository.findByUsername(username);
	        player.put("username", userEntity.getUsername());
	        player.put("email", userEntity.getEmail());

	        return player;
	    }
	 
	 public HashMap<String, String> getByEmail(String email) {
	        //TODO CHECK PARAMS
	        var player = new HashMap<String, String>();
	        var userEntity = playerrepository.findByEmail(email);
	        player.put("username", userEntity.getUsername());
	        player.put("email", userEntity.getEmail());
	        return player;
	    }
	 public List<HashMap<String, String>> getAll() {
	        var payload = new ArrayList<HashMap<String, String>>();

	        List<Player> playerList = playerrepository.findAll();
	        for (var player: playerList) {
	            var newPlayer = new HashMap<String, String>();
	            newPlayer.put("login", player.getUsername());
	            newPlayer.put("email", player.getEmail());
	            payload.add(newPlayer);
	        }
	        return payload;
	    }
	 
	 
	
	 
	 
	  public void update(String usernameTarget, String username, String email, String password) throws Exception {
	        var player = playerrepository.findByUsername(usernameTarget);
	        if (player == null) {
	            throw new Exception(usernameTarget + "doesn't exist"); // TODO make our Exception (404 - Not found)
	        }
	        if (username != null) {
	            player.setUsername(username);;
	        }
	        if (email != null) {
	            player.setEmail(email.toLowerCase());
	        }
	        if (password != null) {
	            player.setPassword(password); //TODO Use BCrypt
	        }
	        playerrepository.save(player);
	    }


		public void updateRessource(Player currentPlayer, int wood, int water, int food, int money)
		{

			currentPlayer.setWood(wood);
			currentPlayer.setWater(water);
			currentPlayer.setFood(food);
			currentPlayer.setMoney(money);

			playerrepository.save(currentPlayer);
			
		}

	    
}
