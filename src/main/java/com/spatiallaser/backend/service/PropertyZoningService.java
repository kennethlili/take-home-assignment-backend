package com.spatiallaser.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spatiallaser.backend.entity.writing.ZoningType;
import com.spatiallaser.backend.enums.ZoningTypeEnum;

@Service
public class PropertyZoningService {

    private final ZoningService zoningService;
    private final AuditService auditService;

    public PropertyZoningService(ZoningService zoningService, AuditService auditService) {
        this.zoningService = zoningService;
        this.auditService = auditService;
    }

    @Transactional("writingTransactionManager")
    public List<ZoningType> updateZoningTypeForProperties(List<Long> propertyIds, ZoningTypeEnum zoningTypeValue) {
        // Perform the zoning type update
        List<ZoningType> updatedZoningTypes = zoningService.bulkUpsertZoningType(propertyIds, zoningTypeValue);

        // Log the activity
        String message = "Updated zoning type for properties: " + propertyIds + " to " + zoningTypeValue;
        auditService.logActivity(message);
        return updatedZoningTypes;
    }

}
