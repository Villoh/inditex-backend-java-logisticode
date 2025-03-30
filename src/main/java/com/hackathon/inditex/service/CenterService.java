package com.hackathon.inditex.service;

import com.hackathon.inditex.model.dto.ResponseMessageDTO;
import com.hackathon.inditex.model.dto.center.CenterCreationDTO;
import com.hackathon.inditex.model.dto.center.CenterDTO;
import com.hackathon.inditex.model.dto.center.CenterUpdateDTO;
import com.hackathon.inditex.model.entity.Center;
import com.hackathon.inditex.model.entity.Order;

import java.util.List;
import java.util.Optional;

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

    /**
     * Retrieves available centers that have capacity for a given order size.
     *
     * @param size The size of the order.
     * @return A list of available centers that can handle the order size.
     */
    List<Center> getAvailableCentersByCapacityAndSize(String size);

    /**
     * Updates only non-null fields of a center entity.
     *
     * @param centerUpdateDTO The DTO containing updated center details.
     * @param center The center entity to update.
     */
    void updateNotNullFields(CenterUpdateDTO centerUpdateDTO, Center center);

    /**
     * Finds the nearest available center for an order.
     *
     * @param order The order to find a center for.
     * @param centers The list of available centers.
     * @return An optional containing the nearest center, if found.
     */
    Optional<Center> findNearestAvailableCenter(Order order, List<Center> centers);

    /**
     * Saves a center entity to the repository.
     *
     * @param center The center entity to save.
     */
    void saveCenter(Center center);

    /**
     * Finds a center by its ID.
     *
     * @param id The ID of the center.
     * @return The found center entity.
     */
    Center findCenterById(Long id);
}
