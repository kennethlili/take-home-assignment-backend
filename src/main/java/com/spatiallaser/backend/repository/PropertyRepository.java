package com.spatiallaser.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.spatiallaser.backend.entity.Property;

/**
 * PostGIS ST Function Reference:
 * https://www.e-education.psu.edu/spatialdb/node/1974
 * 
 * Note: This repo intentionally extends the base Repository interface
 * instead of JpaRepository to expose only the explicitly defined methods.
 * We only need the findByBoundingBox method and don't want to expose any
 * of the standard CRUD operations that would come with JpaRepository.
 */

@org.springframework.stereotype.Repository
public interface PropertyRepository extends Repository<Property, Long> {
        @Query(value = "SELECT p FROM Property p WHERE " +
                        "ST_Intersects(p.geom, ST_MakeEnvelope(:minLon, :minLat, :maxLon, :maxLat, 4326)) = true")
        List<Property> findByBoundingBox(
                        @Param("minLon") double minLon,
                        @Param("minLat") double minLat,
                        @Param("maxLon") double maxLon,
                        @Param("maxLat") double maxLat);

}