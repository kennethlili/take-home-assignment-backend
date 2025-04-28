package com.spatiallaser.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spatiallaser.backend.entity.Property;
import com.spatiallaser.backend.repository.PropertyRepository;

@RestController
// TODO: add CORS configuration
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class PropertyController {

    @Autowired
    private PropertyRepository propertyRepository;

    @GetMapping("/properties")
    public ResponseEntity<List<Property>> getPropertiesInBoundingBox(
            @RequestParam Double west,
            @RequestParam Double south,
            @RequestParam Double east,
            @RequestParam Double north) {

        List<Property> properties;

        properties = propertyRepository.findByBoundingBox(west, south, east, north);

        return ResponseEntity.ok(properties);
    }

}