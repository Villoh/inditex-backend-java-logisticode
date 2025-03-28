package com.hackathon.inditex.repository;

import com.hackathon.inditex.model.entity.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CenterRepository extends JpaRepository<Center, Long> {

    boolean existsByCoordinates_LatitudeAndCoordinates_Longitude(Double latitude, Double longitude);

    @Query(value = """
    SELECT c.*,
           (6371 * ACOS(COS(RADIANS(:lat)) * COS(RADIANS(c.latitude)) *
                        COS(RADIANS(c.longitude) - RADIANS(:lon)) +
                        SIN(RADIANS(:lat)) * SIN(RADIANS(c.latitude)))) AS distance
    FROM centers c
    WHERE c.capacity LIKE %:size%
    ORDER BY distance ASC
    """, nativeQuery = true)
    List<Center> findClosestCenter(@Param("lat") double lat, @Param("lon") double lon, @Param("size") String size);
}
