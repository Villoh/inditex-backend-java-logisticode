package com.hackathon.inditex.validation;

import com.hackathon.inditex.exception.BadRequestException;
import com.hackathon.inditex.model.dto.Coordinates;
import com.hackathon.inditex.model.dto.center.CenterUpdateDTO;
import com.hackathon.inditex.model.entity.Center;
import com.hackathon.inditex.repository.CenterRepository;
import com.hackathon.inditex.util.Constant;
import com.hackathon.inditex.validation.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CenterValidator {

    private final CenterRepository centerRepository;

    /**
     * Validates the creation of a center.
     *
     * @param center The center to be created.
     */
    public void validateCreation(Center center) {
        validateMaxCapacity(center.getCurrentLoad(), center.getMaxCapacity());
        validateCenterExistsByLatAndLong(center.getCoordinates());
    }

    /**
     * Validates the update of a center.
     *
     * @param center The center to be updated.
     * @param centerUpdateDTO The DTO containing the updated information for the center.
     */
    public void validateUpdate(Center center, CenterUpdateDTO centerUpdateDTO) {
        Optional.ofNullable(centerUpdateDTO.getCurrentLoad())
                .ifPresent(load -> validateMaxCapacity(load, center.getMaxCapacity()));
        Optional.ofNullable(centerUpdateDTO.getCoordinates())
                .ifPresent(this::validateCenterExistsByLatAndLong);
    }

    private void validateCenterExistsByLatAndLong(Coordinates coordinates) {
        ValidationUtil.validateNotNullOrBlank(coordinates, Constant.Center.INVALID_INPUT_FIELDS);
        if (centerRepository.existsByCoordinates_LatitudeAndCoordinates_Longitude(coordinates.getLatitude(), coordinates.getLongitude())) {
            throw new BadRequestException(Constant.Center.ALREADY_EXISTS_CENTER_IN_AREA_MESSAGE, null);
        }
    }

    private void validateMaxCapacity(Integer load, Integer maxCapacity) {
        ValidationUtil.validateNotNullOrBlankBulk(Arrays.asList(load, maxCapacity), Constant.Center.INVALID_INPUT_FIELDS);
        ValidationUtil.validateIsHigherThan(load, maxCapacity, Constant.Center.MAX_CAPACITY_EXCEEDED_MESSAGE);
    }
}
