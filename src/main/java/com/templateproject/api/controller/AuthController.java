package com.templateproject.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.templateproject.api.service.AuthService;
import com.templateproject.api.service.ProvinceService;
import com.templateproject.api.service.ResourceService;
import com.templateproject.api.controller.payload.*;
import com.templateproject.api.entity.Player;

/**
 * *
 * @author smaile
 *
 */

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final ProvinceService provinceservice;
    private final ResourceService resourceservice;

    AuthController(AuthService authService, ProvinceService provinceservice,ResourceService resourceservice) {
    	this.provinceservice = provinceservice;
        this.authService = authService;
        this.resourceservice = resourceservice;
    }

//    @PostMapping("/register")
//    public ResponseEntity<Payload> register(@RequestBody PlayerRegister player) {
//        var payload = new Payload();
//        try {
//            int idPlyer = authService.register(
//            		player.getUsername(),
//            		player.getEmail(),
//            		player.getPassword(),
//            		player.getCpassword()
//            );
//            payload.setMessage("PLayer '" + player.getUsername() + "' registered");
//            return new ResponseEntity(payload, HttpStatus.CREATED);
//        } catch (Exception e) {
//            payload.setMessage(e.getMessage());
//            return new ResponseEntity<>(payload, HttpStatus.BAD_REQUEST);
//        }
//    }
    @PostMapping("/register2")
    public ResponseEntity<Payload> register2(@RequestBody PlayerRegister2 player) {
        var payload = new Payload();
        try {
            int idPlayer = authService.register(
            		player.getUsername(),
            		player.getEmail(),
            		player.getPassword(),
            		player.getCpassword()
            );           
            System.out.println("PLAYER ID : " + idPlayer);
            var provinceID = provinceservice.add(player.getProvincename(), idPlayer);
            System.out.println("PROVINCE ID : " + provinceID);
            
            //resourceservice.add(provinceID);
            payload.setMessage("PLayer '" + player.getUsername() + "' registered");	
   
            return new ResponseEntity<Payload>(payload, HttpStatus.CREATED);
        } catch (Exception e) {
            payload.setMessage(e.getMessage());
            return new ResponseEntity<>(payload, HttpStatus.BAD_REQUEST);
        }
    }
    
    
//    @GetMapping("pro")
    
    
    
    @PostMapping("/login")
    public ResponseEntity<Payload> login(@RequestBody PlayerRegister player) {
        var payload = new Payload();
        try {
            String token = authService.login(player.getUsername(), player.getPassword());
            if (token != null) {
                payload.setMessage("player '" + player.getUsername() + "' is connected");
                var data = new HashMap<String, String>();
                data.put("token", token);
                payload.setData(data);
                return new ResponseEntity<>(payload, HttpStatus.OK);
            } else {
                payload.setMessage("Invalid connection");
                return new ResponseEntity<>(payload, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            payload.setMessage(e.getMessage());
            return new ResponseEntity<>(payload, HttpStatus.BAD_REQUEST);
        }
    }


//    @GetMapping("/info")
//    public ResponseEntity<Payload> info(@RequestHeader HttpHeaders header, HttpServletRequest request) {
//    	 String token = header.get("x-token").get(0);
//    	 var payload = new Payload();
//         var playerID = (Integer) request.getAttribute("playerID");
//         try {
//        	 System.out.println("PLAYER ID : " + playerID);
//             var playerInfo = authService.playerInfo(playerID);
//             payload.setMessage("Player informations");
//             payload.setData(playerInfo);
//             return new ResponseEntity<>(payload, HttpStatus.OK);
//         } catch (Exception e) {
//             payload.setMessage(e.getMessage());
//             return new ResponseEntity<>(payload, HttpStatus.BAD_REQUEST);
//         }
//    }


    //@CrossOrigin(origins = "http://localhost:4200")


  /*   Dans le composant qui déclanche le logout: Après avoir reçu la réponse positive du backend indiquant que l'utilisateur a été déconnecté, il faut naviguer vers la page login avec le  routeur Angular. */

    @GetMapping("/logout")
    public ResponseEntity<Payload> logout(@RequestHeader HttpHeaders headers) {
        var payload = new Payload();
        String token = headers.get("x-token").get(0);
        authService.logout(token);
        payload.setMessage("player logout");

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Location", "/login");

        return new ResponseEntity<>(payload, HttpStatus.SEE_OTHER);
    }
    
  @GetMapping("/info")
  public ResponseEntity<Payload> info(@RequestHeader HttpHeaders headers) {
      var payload = new Payload();
      var token = headers.get("x-token").get(0);
      try {
          var playerInfo = authService.playerInfo(token);
          
          payload.setMessage("player informations");
          payload.setData(playerInfo);
          return new ResponseEntity<>(payload, HttpStatus.OK);
      } catch (Exception e) {
          payload.setMessage(e.getMessage());
          return new ResponseEntity<>(payload, HttpStatus.BAD_REQUEST);
      }
  }
  
  @GetMapping("/connectedPlayers")
  public ResponseEntity<Payload> getConnectedPlayers() {
      var payload = new Payload();
      try {
          payload.setData(authService.getConnectedPLayers());
          payload.setMessage("Get all connected players");
          return new ResponseEntity<>(payload, HttpStatus.OK);
      } catch (Exception e) {
          payload.setMessage(e.getMessage());
          payload.setData(null);
          return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }
  @GetMapping("/otherConnectedPlayers")
  public ResponseEntity<Payload> getConnectedPlayers(@RequestHeader HttpHeaders headers) {
      var payload = new Payload();
      try {
    	  var token = headers.get("x-token").get(0);
          payload.setData(authService.getOtherConnectedPLayers(token));
          payload.setMessage("Get all connected players");
          return new ResponseEntity<>(payload, HttpStatus.OK);
      } catch (Exception e) {
          payload.setMessage(e.getMessage());
          payload.setData(null);
          return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

  @GetMapping("/fakeotherconnectedplayers")
    public ResponseEntity<Payload> getfakeotherConnectedPlayers() 
{

      var payload = new Payload();

var playerList = new ArrayList<HashMap<String, Object>>();

var newPlayer = new HashMap<String, Object>();
newPlayer.put("login", "l1");
newPlayer.put("email", "e1");
newPlayer.put("id", 1);
playerList.add(newPlayer);

var newPlayer2 = new HashMap<String, Object>();
newPlayer2.put("login", "l2");
newPlayer2.put("email", "e2");
newPlayer2.put("id", 2);
playerList.add(newPlayer2);

var newPlayer3 = new HashMap<String, Object>();
newPlayer3.put("login", "l3");
newPlayer3.put("email", "e3");
newPlayer3.put("id", 3);
playerList.add(newPlayer3);

payload.setData(playerList);

payload.setMessage("Get all faked connected players");

return new ResponseEntity<>(payload, HttpStatus.OK);



}

    
}