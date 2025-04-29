package com.spatiallaser.backend.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.spatiallaser.backend.entity.reading.Property;
import com.spatiallaser.backend.entity.writing.ZoningType;
import com.spatiallaser.backend.repository.reading.PropertyRepository;
import com.spatiallaser.backend.repository.writing.ZoningTypeRepository;

public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final ZoningTypeRepository zoningTypeRepository;

    public PropertyService(PropertyRepository propertyRepository, ZoningTypeRepository zoningTypeRepository) {
        this.propertyRepository = propertyRepository;
        this.zoningTypeRepository = zoningTypeRepository;
    }

    public List<Property> getPropertiesWithUpdatedZoning(double west, double south,
            double east, double north) {
        List<Property> properties = propertyRepository.findByBoundingBox(west, south, east, north);

        if (properties.isEmpty()) {
            return properties;
        }

        // Extract property IDs
        List<Long> propertyIds = properties.stream()
                .map(Property::getId)
                .collect(Collectors.toList());

        List<ZoningType> zoningUpdates = zoningTypeRepository.findByPropertyRefIdIn(propertyIds);

        Map<Long, ZoningType> zoningUpdateMap = zoningUpdates.stream()
                .collect(Collectors.toMap(ZoningType::getPropertyRefId, update -> update));

        // Apply updates to the properties
        properties.forEach(property -> {
            ZoningType update = zoningUpdateMap.get(property.getId());
            if (update != null) {
                property.setZoningType(update.getZoningType());
            }
        });

        return properties;
    }

}
