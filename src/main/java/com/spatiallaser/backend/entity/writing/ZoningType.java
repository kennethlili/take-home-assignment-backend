package com.spatiallaser.backend.entity.writing;

import java.time.LocalDateTime;

import com.spatiallaser.backend.enums.ZoningTypeEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "property_zoning_updates")
public class ZoningType {

    // assuming we dont have to keep the record of the updated zoning type
    @Id
    @Column(name = "property_ref_id", nullable = false)
    private Long propertyRefId;

    @Enumerated(EnumType.STRING)
    @Column(name = "zoning_type", nullable = false)
    private ZoningTypeEnum zoningType;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    // Constructors
    public ZoningType() {
    }

    public ZoningType(Long propertyRefId, ZoningTypeEnum zoningType) {
        this.propertyRefId = propertyRefId;
        this.zoningType = zoningType;
    }

    public Long getPropertyRefId() {
        return propertyRefId;
    }

    public void setPropertyRefId(Long propertyRefId) {
        this.propertyRefId = propertyRefId;
    }

    public ZoningTypeEnum getZoningType() {
        return zoningType;
    }

    public void setZoningType(ZoningTypeEnum zoningType) {
        this.zoningType = zoningType;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
