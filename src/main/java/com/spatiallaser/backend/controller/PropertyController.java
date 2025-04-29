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
import com.spatiallaser.backend.service.PropertyZoningService;

import jakarta.validation.Valid;

@RestController
// TODO: add CORS configuration
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class PropertyController {

    private final PropertyRepository propertyRepository;
    private final PropertyZoningService propertyZoningService;

    public PropertyController(PropertyRepository propertyRepository, PropertyZoningService propertyZoningService) {
        this.propertyZoningService = propertyZoningService;
        this.propertyRepository = propertyRepository;
    }

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

    @PutMapping("/properties/zoning")
    public ResponseEntity<List<ZoningType>> upsertZoningTypes(
            @Valid @RequestBody ZoningUpdateRequest request) {

        List<ZoningType> updatedZoningTypes = propertyZoningService.updateZoningTypeForProperties(
                request.getPropertyIds(),
                request.getZoningType());

        return ResponseEntity.ok(updatedZoningTypes);
    }

}
