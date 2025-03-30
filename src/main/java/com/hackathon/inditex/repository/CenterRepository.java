package com.hackathon.inditex.repository;

import com.hackathon.inditex.model.dto.Coordinates;
import com.hackathon.inditex.model.entity.Center;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for managing {@link Center} entities.
 * Provides methods for database operations related to centers.
 */
public interface CenterRepository extends JpaRepository<Center, Long> {

    /**
     * Checks if a center exists at the given coordinates.
     *
     * @param coordinates the coordinates to check
     * @return true if a center exists at the given coordinates, false otherwise
     */
    boolean existsByCoordinates(Coordinates coordinates);

    /**
     * Finds all centers with the specified status.
     *
     * @param status the status of the centers to find
     * @return a list of centers matching the given status
     */
    List<Center> findByStatus(String status);
}
