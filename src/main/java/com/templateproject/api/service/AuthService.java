package com.templateproject.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;


import com.templateproject.api.repository.PlayerRepository;
import com.templateproject.api.entity.*;

import at.favre.lib.crypto.bcrypt.BCrypt;
/**
 * *
 * @author smaile
 *
 */
@Service
public class AuthService {
	private List<Token> tokens;

    private final PlayerRepository playerRepository;
    private final ProvinceService provinceService;
    private final ResourceService resourceService;


    private final static int DEFAULT_WOOD = 50;
    private final static int DEFAULT_WATER = 100;
    private final static int DEFAULT_FOOD = 50;
    private final static int DEFAULT_MONEY = 100;

    AuthService(PlayerRepository playerRepository,
    		ProvinceService provinceService,
    		ResourceService resourceService
    		) {
        /*this.userRepository = userRepository;*/
        this.provinceService = provinceService;
        this.resourceService = resourceService;
        this.playerRepository = playerRepository;
        tokens = new ArrayList<>();
    }

    public int register(String login, String email, String password, String cpassword) throws Exception {
        //TODO Verify params
        if (!login.isEmpty() && !email.isEmpty() && password.equals(cpassword)) {
            String passwordHashed = BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, password.toCharArray());
            Player player = new Player();
            player.setUsername(login);
            player.setEmail(email);
            player.setPassword(passwordHashed);


            player.setWood(DEFAULT_WOOD);
            player.setWater(DEFAULT_WATER);
            player.setFood(DEFAULT_FOOD);
            player.setMoney(DEFAULT_MONEY);


            player = playerRepository.save(player);
            return player.getId();
        } else {
            throw new Exception("Invalid params"); //TODO specific Exception
        }
        //TODO ERROR
    }

//    public String login(String login, String password) {
//        Player dbUser = userRepository.findByUsername(login);
//        var result = BCrypt.verifyer().verify(password.toCharArray(), dbUser.getPassword());
//        if (result.verified) {
//            var token = GenerateToken.newUserToken(dbUser.getId());
//            tokens.add(new Token(dbUser.getId(),token));
//            return token;
//        }
//        return null;
//    }
    
    public String login(String login, String password) {
        Player dbUser = playerRepository.findByUsername(login);
        if (dbUser != null) {
            var result = BCrypt.verifyer().verify(password.toCharArray(), dbUser.getPassword());
            if (result.verified) {
                var token = GenerateToken.newUserToken(dbUser.getId());
                tokens.add(new Token(dbUser.getId(), token));
                return token;
            }
        }
        return null;
    }

    
    

    public Integer findPlayerIdByToken(String token) {
        for (Token item : tokens) {
            if (item.getToken().equals(token)) {
                return item.getUserID();
            }
        }
        return null;
    }


    public Map<String, String> playerInfo(Integer playerID) throws Exception {
        Player player = playerRepository.findById(playerID).get();
        var playerInfo = new HashMap<String, String>();
        playerInfo.put("login", player.getUsername());
        playerInfo.put("email", player.getEmail());
        return playerInfo;
    }

    public void logout(String token) {
        for (Token item : tokens) {
            if (item.getToken().equals(token)) {
                tokens.remove(item);
                return;
            }
        }
    }
    
  public Map<String, String> playerInfo(String token) throws Exception {
  var playerID = findPlayerIdByToken(token);
  var playerInfo = new HashMap<String, String>();

  var provinceInfo = provinceService.getProvinceByPlayerId(playerID).get(0);
 /* Integer provinceID = (provinceService.getProvinceByPlayerId(playerID).get(0)).getId();
   var sourceInfo = resourceService.getResourceByProvinceId(provinceID); */

  if (playerID == null) {
      throw new Exception("Invalid TOKEN");
  }
	  try {
		var player = playerRepository.findById(playerID).get();
		 
		 playerInfo.put("login", player.getUsername());
		 playerInfo.put("email", player.getEmail());

		playerInfo.put("provinceName", provinceInfo.getName());
		 playerInfo.put("provincePopulation", Integer.toString(provinceInfo.getPopulation()));

		 int wood = player.getWood();
		 playerInfo.put("wood", (Integer.toString(wood)));

		 int water = player.getWater();
		 playerInfo.put("water", (Integer.toString(water)));

		 int food = player.getFood();
		 playerInfo.put("food", (Integer.toString(food)));
        
         int money = player.getMoney();
		 playerInfo.put("money", (Integer.toString(money)));
		 
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return playerInfo;
  }


  public Player getPlayerByToken(String token)
  {

      var playerID = findPlayerIdByToken(token);

    var player = playerRepository.findById(playerID).get();

    return player;

  }
}
