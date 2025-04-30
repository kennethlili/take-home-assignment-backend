package com.spatiallaser.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spatiallaser.backend.dto.ZoningUpdateRequest;
import com.spatiallaser.backend.entity.reading.Property;
import com.spatiallaser.backend.entity.writing.ZoningType;
import com.spatiallaser.backend.repository.reading.PropertyRepository;
import com.spatiallaser.backend.service.PropertyService;
import com.spatiallaser.backend.service.PropertyZoningService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "https://spatial-laser-frontend.onrender.com")
@RequestMapping("/api")
public class PropertyController {

    private final PropertyZoningService propertyZoningService;
    private final PropertyService propertyService;

    public PropertyController(PropertyRepository propertyRepository, PropertyZoningService propertyZoningService, PropertyService propertyService) {
        this.propertyZoningService = propertyZoningService;
        this.propertyService = propertyService;
    }

    @GetMapping("/properties")
    public ResponseEntity<List<Property>> getPropertiesInBoundingBox(
            @RequestParam Double west,
            @RequestParam Double south,
            @RequestParam Double east,
            @RequestParam Double north) {

        List<Property> properties;

        properties = propertyService.getPropertiesWithUpdatedZoning(west, south, east, north);

        return ResponseEntity.ok(properties);
    }

    @PutMapping("/properties/zoning")
    public ResponseEntity<List<ZoningType>> upsertZoningTypes(
            @Valid @RequestBody ZoningUpdateRequest request) {

        List<ZoningType> updatedZoningTypes = propertyZoningService.updateZoningTypeForProperties(
                request.getPropertyIds(),
                request.getZoningType());

        return ResponseEntity.ok(updatedZoningTypes);
    }

}
