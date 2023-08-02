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
import com.templateproject.api.controller.payload.RessourcePayload;
import com.templateproject.api.entity.Resource;
import com.templateproject.api.service.ResourceService;

/**
 * *
 * @author smaile
 *
 */
@RestController
public class ResourceController {
    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @PostMapping("/resource")
    public ResponseEntity<Payload> addResource(@RequestBody RessourcePayload resource) {
        var payload = new Payload();
        try {
            resourceService.add(resource.getProvinceID(),resource.getWood(), resource.getWater(), resource.getFood() );
            payload.setMessage("Resource added");
            return new ResponseEntity<>(payload, HttpStatus.CREATED);
        } catch (Exception e) {
            payload.setMessage(e.getMessage());
            return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/resource/{id}")
    public ResponseEntity<Payload> deleteResourceById(@PathVariable int id) {
        var payload = new Payload();
        try {
            resourceService.deleteById(id);
            payload.setMessage("Resource with ID " + id + " deleted");
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (Exception e) {
            payload.setMessage(e.getMessage());
            return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/resources")
    public ResponseEntity<Payload> getAllResources() {
        var payload = new Payload();
        try {
            payload.setData(resourceService.getAll());
            payload.setMessage("All Resources");
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (Exception e) {
            payload.setMessage(e.getMessage());
            payload.setData(null);
            return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/resource/{id}")
    public ResponseEntity<Payload> getResourceById(@PathVariable int id) {
        var payload = new Payload();
        try {
            var resource = resourceService.getById(id);
            payload.setData(resource);
            payload.setMessage("Resource with ID " + id);
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (Exception e) {
            payload.setMessage(e.getMessage());
            payload.setData(null);
            return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/resource/{id}")
    public ResponseEntity<Payload> updateResource(@PathVariable int id, @RequestBody RessourcePayload resource) {
        var payload = new Payload();
        try {
            resourceService.update(id, resource.getWood(), resource.getWater(), resource.getFood());
            payload.setMessage("Resource updated");
            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (Exception e) {
            payload.setMessage(e.getMessage());
            return new ResponseEntity<>(payload, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

