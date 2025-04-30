package com.spatiallaser.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spatiallaser.backend.entity.writing.ZoningType;
import com.spatiallaser.backend.enums.ZoningTypeEnum;
import com.spatiallaser.backend.repository.writing.ZoningTypeRepository;

@Service
public class ZoningService {

    private final ZoningTypeRepository zoningTypeRepository;

    public ZoningService(ZoningTypeRepository zoningTypeRepository) {
        this.zoningTypeRepository = zoningTypeRepository;
    }

    @Transactional("writingTransactionManager")
    public List<ZoningType> bulkUpsertZoningType(List<Long> propertyIds, ZoningTypeEnum zoningTypeValue) {
        List<ZoningType> zoningTypesToSave = new ArrayList<>(propertyIds.size());

        for (Long propertyId : propertyIds) {
            zoningTypesToSave.add(new ZoningType(propertyId, zoningTypeValue));
        }

        // Save all entities in a single transaction
        return zoningTypeRepository.saveAll(zoningTypesToSave);
    }
}
