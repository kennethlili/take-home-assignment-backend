package com.spatiallaser.backend.dto;

import java.util.List;

import com.spatiallaser.backend.enums.ZoningTypeEnum;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ZoningUpdateRequest {

    @NotEmpty(message = "Property IDs list cannot be empty")
    private List<Long> propertyIds;

    @NotNull(message = "Zoning type cannot be null")
    private ZoningTypeEnum zoningType;

    // Getters and setters
    public List<Long> getPropertyIds() {
        return propertyIds;
    }

    public void setPropertyIds(List<Long> propertyIds) {
        this.propertyIds = propertyIds;
    }

    public ZoningTypeEnum getZoningType() {
        return zoningType;
    }

    public void setZoningType(ZoningTypeEnum zoningType) {
        this.zoningType = zoningType;
    }
}
