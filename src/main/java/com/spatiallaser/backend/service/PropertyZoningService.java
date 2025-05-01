package com.spatiallaser.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spatiallaser.backend.entity.reading.Property;
import com.spatiallaser.backend.entity.writing.ZoningType;
import com.spatiallaser.backend.enums.ZoningTypeEnum;
import com.spatiallaser.backend.exception.BadRequestException;
import com.spatiallaser.backend.exception.DatabaseOperationException;
import com.spatiallaser.backend.exception.ResourceNotFoundException;
import com.spatiallaser.backend.repository.reading.PropertyRepository;

@Service
public class PropertyZoningService {

    private final ZoningService zoningService;
    private final AuditService auditService;
    private final PropertyRepository propertyRepository;

    public PropertyZoningService(ZoningService zoningService, AuditService auditService, PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
        this.zoningService = zoningService;
        this.auditService = auditService;
    }

    @Transactional("writingTransactionManager")
    public List<ZoningType> updateZoningTypeForProperties(List<Long> propertyIds, ZoningTypeEnum zoningTypeValue) {
        try {
            // Check if the propertyIds exist in the propertyRepository
            List<Property> existingPropertyIds = propertyRepository.findAllById(propertyIds);
            if (propertyIds.size() != existingPropertyIds.size()) {
                throw new ResourceNotFoundException("Some property IDs do not exist.");
            }
            // Perform the zoning type update
            List<ZoningType> updatedZoningTypes = zoningService.bulkUpsertZoningType(propertyIds, zoningTypeValue);

            // Log the activity
            String message = "Updated zoning type for properties: " + propertyIds + " to " + zoningTypeValue;
            auditService.logActivity(message);
            return updatedZoningTypes;
        } catch (DatabaseOperationException e) {
            throw new DatabaseOperationException("Failed to update zoning types: " + e.getMessage(), e);
        } catch (ResourceNotFoundException | BadRequestException e) {
            throw e;
        } catch (Exception e) {
            // Catch any other exceptions and wrap them
            throw new DatabaseOperationException("An unexpected error occurred while updating zoning types: " + e.getMessage(), e);
        }

    }

}
