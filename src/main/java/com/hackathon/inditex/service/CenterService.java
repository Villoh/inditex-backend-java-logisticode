package com.hackathon.inditex.service;

import com.hackathon.inditex.model.dto.ResponseMessageDTO;
import com.hackathon.inditex.model.dto.center.CenterCreationDTO;
import com.hackathon.inditex.model.dto.center.CenterDTO;
import com.hackathon.inditex.model.dto.center.CenterUpdateDTO;
import com.hackathon.inditex.model.entity.Center;

import java.util.List;

/**
 * Service interface for managing centers.
 * This interface defines methods for creating, retrieving, updating, and deleting centers.
 */
public interface CenterService {

    /**
     * Creates a new center.
     *
     * @param centerCreationDTO A DTO containing the details of the center to be created.
     * @return A {@link ResponseMessageDTO} containing the result of the operation, including
     *         success or failure message.
     */
    ResponseMessageDTO createCenter(CenterCreationDTO centerCreationDTO);

    /**
     * Retrieves all available centers.
     *
     * @return A list of {@link CenterDTO} containing the details of all centers.
     */
    List<CenterDTO> getAllCenters();

    /**
     * Updates an existing center.
     *
     * @param id The ID of the center to be updated.
     * @param centerUpdateDTO A DTO containing the updated information for the center.
     * @return A {@link ResponseMessageDTO} containing the result of the update operation,
     *         including success or failure message.
     */
    ResponseMessageDTO updateCenter(Long id, CenterUpdateDTO centerUpdateDTO);

    /**
     * Deletes an existing center.
     *
     * @param id The ID of the center to be deleted.
     * @return A {@link ResponseMessageDTO} containing the result of the delete operation,
     *         including success or failure message.
     */
    ResponseMessageDTO deleteCenter(Long id);

    List<Center> getAvailableCentersByCapacityAndSize(String size);
}
