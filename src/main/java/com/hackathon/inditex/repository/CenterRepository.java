package com.hackathon.inditex.repository;

import com.hackathon.inditex.model.entity.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CenterRepository extends JpaRepository<Center, Long> {

    boolean existsByCoordinates_LatitudeAndCoordinates_Longitude(Double latitude, Double longitude);

    /**
     * Finds centers filtered by capacity size.
     *
     * @param size Capacity size filter.
     * @return A list of centers matching the capacity size.
     */
    @Query("SELECT c FROM Center c WHERE c.capacity LIKE %:size%")
    List<Center> findCentersByCapacitySize(@Param("size") String size);
}
