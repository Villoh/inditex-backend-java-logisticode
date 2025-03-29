package com.hackathon.inditex.repository;

import com.hackathon.inditex.model.dto.Coordinates;
import com.hackathon.inditex.model.entity.Center;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CenterRepository extends JpaRepository<Center, Long> {

    boolean existsByCoordinates(Coordinates coordinates);

    List<Center> findByStatus(String status);
}
