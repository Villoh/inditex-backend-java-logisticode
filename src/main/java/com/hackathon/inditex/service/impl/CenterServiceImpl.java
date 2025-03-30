package com.hackathon.inditex.service.impl;

import com.hackathon.inditex.exception.NotFoundException;
import com.hackathon.inditex.mapper.CenterMapper;
import com.hackathon.inditex.model.dto.ResponseMessageDTO;
import com.hackathon.inditex.model.dto.center.CenterCreationDTO;
import com.hackathon.inditex.model.dto.center.CenterDTO;
import com.hackathon.inditex.model.dto.center.CenterUpdateDTO;
import com.hackathon.inditex.model.entity.Center;
import com.hackathon.inditex.model.entity.Order;
import com.hackathon.inditex.repository.CenterRepository;
import com.hackathon.inditex.service.CenterService;
import com.hackathon.inditex.util.Constant;
import com.hackathon.inditex.util.GeoUtil;
import com.hackathon.inditex.validation.CenterValidator;
import com.hackathon.inditex.validation.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link CenterService} interface.
 * This class provides methods to create, retrieve, update, and delete centers. It also includes validation logic for creating new centers.
 */
@Service
@RequiredArgsConstructor
public class CenterServiceImpl implements CenterService {

    private final CenterRepository centerRepository;
    private final CenterMapper centerMapper;
    private final CenterValidator centerValidator;

    /**
     * Creates a new center and saves it to the repository.
     *
     * @param centerCreationDTO The DTO containing center details.
     * @return A response message indicating success.
     */
    @Override
    public ResponseMessageDTO createCenter(CenterCreationDTO centerCreationDTO) {
        Center newCenter = centerMapper.centerDtoToCenter(centerCreationDTO);
        centerValidator.validateCreation(newCenter);
        saveCenter(newCenter);
        return ResponseMessageDTO.builder()
                .message(Constant.Center.SUCCESSFUL_CREATION_MESSAGE)
                .build();
    }

    /**
     * Retrieves all centers from the repository.
     *
     * @return A list of center DTOs.
     */
    @Override
    public List<CenterDTO> getAllCenters() {
        List<Center> centers = centerRepository.findAll();
        return centerMapper.centersToCenterDtos(centers);
    }

    /**
     * Updates an existing center with provided details.
     *
     * @param id The ID of the center to update.
     * @param centerUpdateDTO The DTO containing updated center details.
     * @return A response message indicating success.
     */
    @Override
    public ResponseMessageDTO updateCenter(Long id, CenterUpdateDTO centerUpdateDTO) {
        Center center = findCenterById(id);
        centerValidator.validateUpdate(centerUpdateDTO);
        updateNotNullFields(centerUpdateDTO, center);
        centerValidator.validateMaxCapacityOnUpdate(centerUpdateDTO.getCurrentLoad(), center.getMaxCapacity());
        saveCenter(center);
        return ResponseMessageDTO.builder()
                .message(Constant.Center.UPDATED_CENTER)
                .build();
    }

    /**
     * Deletes a center by its ID.
     *
     * @param id The ID of the center to delete.
     * @return A response message indicating success.
     * @throws NotFoundException if the center is not found.
     */
    @Override
    public ResponseMessageDTO deleteCenter(Long id) {
        centerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Constant.Center.CENTER_NOT_FOUND));
        centerRepository.deleteById(id);
        return ResponseMessageDTO.builder()
                .message(Constant.Center.DELETED_CENTER)
                .build();
    }

    /**
     * Finds a center by its ID.
     *
     * @param id The ID of the center.
     * @return The found center entity.
     * @throws NotFoundException if the center is not found.
     */
    @Override
    public Center findCenterById(Long id) {
        return centerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Constant.Center.CENTER_NOT_FOUND));
    }

    /**
     * Saves a center entity to the repository.
     *
     * @param center The center entity to save.
     */
    @Override
    public void saveCenter(Center center) {
        centerRepository.save(center);
    }

    /**
     * Updates only non-null fields of a center entity.
     *
     * @param centerUpdateDTO The DTO containing updated center details.
     * @param center The center entity to update.
     */
    @Override
    public void updateNotNullFields(CenterUpdateDTO centerUpdateDTO, Center center) {
        Optional.ofNullable(centerUpdateDTO.getName()).ifPresent(center::setName);
        Optional.ofNullable(centerUpdateDTO.getCapacity()).ifPresent(center::setCapacity);
        Optional.ofNullable(centerUpdateDTO.getStatus()).ifPresent(center::setStatus);
        Optional.ofNullable(centerUpdateDTO.getMaxCapacity()).ifPresent(center::setMaxCapacity);
        Optional.ofNullable(centerUpdateDTO.getCoordinates()).ifPresent(center::setCoordinates);
        Optional.ofNullable(centerUpdateDTO.getCurrentLoad()).ifPresent(center::setCurrentLoad);
    }

    /**
     * Retrieves available centers that have capacity for a given order size.
     *
     * @param size The size of the order.
     * @return A list of available centers that can handle the order size.
     */
    @Override
    public List<Center> getAvailableCentersByCapacityAndSize(String size) {
        return centerRepository.findByStatus(Constant.Center.STATUS_AVAILABLE).stream()
                .filter(center -> ValidationUtil.isSmallerThan(center.getCurrentLoad(), center.getMaxCapacity()))
                .filter(center -> center.getCapacity().contains(size))
                .toList();
    }

    /**
     * Finds the nearest available center for an order.
     *
     * @param order The order to find a center for.
     * @param centers The list of available centers.
     * @return An optional containing the nearest center, if found.
     */
    @Override
    public Optional<Center> findNearestAvailableCenter(Order order, List<Center> centers) {
        return centers.stream()
                .min(Comparator.comparingDouble(center -> GeoUtil.calculateDistance(
                        order.getCoordinates().getLatitude(),
                        order.getCoordinates().getLongitude(),
                        center.getCoordinates().getLatitude(),
                        center.getCoordinates().getLongitude()
                )));
    }
}