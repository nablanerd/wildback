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

    private final PlayerRepository userRepository;

    AuthService(PlayerRepository userRepository) {
        this.userRepository = userRepository;
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
            player = userRepository.save(player);
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
        Player dbUser = userRepository.findByUsername(login);
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
        Player player = userRepository.findById(playerID).get();
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
    
//  public Map<String, String> playerInfo(String token) throws Exception {
//  var userID = findPlayerIdByToken(token);
//  if (userID == null) {
//      throw new Exception("Invalid TOKEN");
//  }
//  var user = userRepository.findById(userID).get();
//  var userInfo = new HashMap<String, String>();
//  userInfo.put("login", user.getUsername());
//  userInfo.put("email", user.getEmail());
//  return userInfo;
//}
}
