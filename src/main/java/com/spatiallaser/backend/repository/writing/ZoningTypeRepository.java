package com.spatiallaser.backend.repository.writing;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spatiallaser.backend.entity.writing.ZoningType;

@Repository
public interface ZoningTypeRepository extends JpaRepository<ZoningType, Long> {

    List<ZoningType> findByPropertyRefIdIn(List<Long> propertyRefIds);
}
