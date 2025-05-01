package com.spatiallaser.backend.repository.reading;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spatiallaser.backend.entity.reading.Property;

/**
 * PostGIS ST Function Reference:
 * https://www.e-education.psu.edu/spatialdb/node/1974
 *
 */
@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    @Query(value = "SELECT p FROM Property p WHERE "
            + "ST_Intersects(p.geom, ST_MakeEnvelope(:minLon, :minLat, :maxLon, :maxLat, 4326)) = true")
    List<Property> findByBoundingBox(
            @Param("minLon") double minLon,
            @Param("minLat") double minLat,
            @Param("maxLon") double maxLon,
            @Param("maxLat") double maxLat);

}
