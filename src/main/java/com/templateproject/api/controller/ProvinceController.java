package com.templateproject.api.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.templateproject.api.controller.payload.Payload;
import com.templateproject.api.controller.payload.ProvincePayload;

import com.templateproject.api.entity.Province;

import com.templateproject.api.service.AuthService;
import com.templateproject.api.service.ProvinceService;


/**
 * *
 * @author smaile
 *
 */
@RestController
public class ProvinceController {
    private final ProvinceService provinceService;
    private final AuthService authservice;
    public ProvinceController(ProvinceService provinceService, AuthService authservice) {
        this.provinceService = provinceService;
        this.authservice = authservice;
    }

    @PostMapping("/province")
    public ResponseEntity<Payload> addProvince(@RequestBody ProvincePayload province) {    	
        var payload = new Payload();
        try {
            provinceService.add(province.getName(),/* province.getPopulation(), */province.getPlayerID());
            payload.setMessage("Province added");
            return new ResponseEntity<>(payload, HttpStatus.CREATED);
        } catch (Exception e) {
            payload.setMessage(e.getMessage());
            return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/province/{id}")
    public ResponseEntity<Payload> deleteProvinceById(@PathVariable int id) {
        var payload = new Payload();
        try {
            provinceService.deleteById(id);
            payload.setMessage("Province with ID " + id + " deleted");
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (Exception e) {
            payload.setMessage(e.getMessage());
            return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/province/name/{name}")
    public ResponseEntity<Payload> deleteProvinceByName(@PathVariable String name) {
        var payload = new Payload();
        try {
            provinceService.deleteByName(name);
            payload.setMessage("Province with name '" + name + "' deleted");
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (Exception e) {
            payload.setMessage(e.getMessage());
            return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/provinces")
    public ResponseEntity<Payload> getAllProvinces() {
        var payload = new Payload();
        try {
            payload.setData(provinceService.getAll());
            payload.setMessage("All Provinces");
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (Exception e) {
            payload.setMessage(e.getMessage());
            payload.setData(null);
            return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/provincesBID")
    public ResponseEntity<Payload> getAllProvincesByPLayerId(@RequestHeader HttpHeaders headers) {
    	var token = headers.get("x-token").get(0);
    	var playerId = authservice.findPlayerIdByToken(token);
        var payload = new Payload();
        try {
        	payload.setData(provinceService.getProvinceByPlayerId(playerId));
	        payload.setMessage(" all provinces for the player id" + playerId);
	        return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch(Exception e){
        	payload.setMessage(e.getMessage());
        	payload.setData(null);
        	return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/province/{id}")
    public ResponseEntity<Payload> getProvinceById(@PathVariable int id) {
        var payload = new Payload();
        try {
            var province = provinceService.getById(id);
            payload.setData(province);
            payload.setMessage("Province with ID " + id);
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (Exception e) {
            payload.setMessage(e.getMessage());
            payload.setData(null);
            return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/province/name/{name}")
    public ResponseEntity<Payload> getProvinceByName(@PathVariable String name) {
        var payload = new Payload();
        try {
            var province = provinceService.getByName(name);
            payload.setData(province);
            payload.setMessage("Province with name '" + name + "'");
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (Exception e) {
            payload.setMessage(e.getMessage());
            payload.setData(null);
            return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/province/{id}")
    public ResponseEntity<Payload> updateProvince(@PathVariable int id, @RequestBody Province province) {
        var payload = new Payload();
        try {
//            provinceService.update(id, province.getName(), province.getPopulation(), province.getPlayer());
            provinceService.update(id, province.getName(), province.getPopulation());

            payload.setMessage("Province updated");
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (Exception e) {
            payload.setMessage(e.getMessage());
            return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
