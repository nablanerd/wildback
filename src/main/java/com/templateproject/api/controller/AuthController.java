package com.templateproject.api.controller;

import java.util.HashMap;

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
import com.templateproject.api.repository.PlayerRepository;
import com.templateproject.api.service.AuthService;
import com.templateproject.api.service.PlayerService;
import com.templateproject.api.service.ProvinceService;
import com.templateproject.api.service.ResourceService;

import jakarta.servlet.http.HttpServletRequest;

import com.templateproject.api.controller.payload.*;

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

    @PostMapping("/register")
    public ResponseEntity<Payload> register(@RequestBody PlayerRegister player) {
        var payload = new Payload();
        try {
            int idPlyer = authService.register(
            		player.getUsername(),
            		player.getEmail(),
            		player.getPassword(),
            		player.getCpassword()
            );
            payload.setMessage("PLayer '" + player.getUsername() + "' registered");
            return new ResponseEntity(payload, HttpStatus.CREATED);
        } catch (Exception e) {
            payload.setMessage(e.getMessage());
            return new ResponseEntity<>(payload, HttpStatus.BAD_REQUEST);
        }
    }
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
            resourceservice.add(provinceID);
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


    @GetMapping("/info")
    public ResponseEntity<Payload> info(@RequestHeader HttpHeaders header, HttpServletRequest request) {
    	 String token = header.get("x-token").get(0);
    	 var payload = new Payload();
         var playerID = (Integer) request.getAttribute("playerID");
         try {
        	 System.out.println("PLAYER ID : " + playerID);
             var playerInfo = authService.playerInfo(playerID);
             payload.setMessage("Player informations");
             payload.setData(playerInfo);
             return new ResponseEntity<>(payload, HttpStatus.OK);
         } catch (Exception e) {
             payload.setMessage(e.getMessage());
             return new ResponseEntity<>(payload, HttpStatus.BAD_REQUEST);
         }
    }


  /*   Dans le composant qui déclanche le logout: Après avoir reçu la réponse positive du backend indiquant que l'utilisateur a été déconnecté, il faut naviguer vers la page login avec le  routeur Angular. */
    @GetMapping("/logout")
    public ResponseEntity<Payload> logout(@RequestHeader HttpHeaders headers) {
        var payload = new Payload();
        var token = headers.get("x-token").get(0);
        authService.logout(token);
        payload.setMessage("player logout");

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Location", "/login");

        return new ResponseEntity<>(payload, HttpStatus.SEE_OTHER);
    }
    
//  @GetMapping("/info")
//  public ResponseEntity<Payload> info(@RequestHeader HttpHeaders headers) {
//      var payload = new Payload();
//      var token = headers.get("x-token").get(0);
//      try {
//          var playerInfo = authService.userInfo(token);
//          payload.setMessage("player informations");
//          payload.setData(playerInfo);
//          return new ResponseEntity<>(payload, HttpStatus.OK);
//      } catch (Exception e) {
//          payload.setMessage(e.getMessage());
//          return new ResponseEntity<>(payload, HttpStatus.BAD_REQUEST);
//      }
//  }
    
}