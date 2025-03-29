package com.hackathon.inditex.validation;

import com.hackathon.inditex.exception.InternalServerErrorException;
import com.hackathon.inditex.model.dto.Coordinates;
import com.hackathon.inditex.model.dto.center.CenterUpdateDTO;
import com.hackathon.inditex.model.entity.Center;
import com.hackathon.inditex.repository.CenterRepository;
import com.hackathon.inditex.util.Constant;
import com.hackathon.inditex.validation.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
        Optional.ofNullable(center.getCoordinates()).ifPresent(this::validateCenterExistsByCoordinates);
    }

    /**
     * Validates the update of a center.
     *
     * @param centerUpdateDTO The DTO containing the updated information for the center.
     */
    public void validateUpdate(CenterUpdateDTO centerUpdateDTO) {
        Optional.ofNullable(centerUpdateDTO.getCoordinates()).ifPresent(this::validateCenterExistsByCoordinates);
    }


    private void validateCenterExistsByCoordinates(Coordinates coordinates) {
        Optional.ofNullable(coordinates).orElseThrow(() -> new InternalServerErrorException(Constant.Center.INVALID_INPUT_FIELDS, null));
        Optional.of(coordinates)
                .filter(centerRepository::existsByCoordinates)
                .ifPresent(c -> { throw new InternalServerErrorException(Constant.Center.ALREADY_EXISTS_CENTER_IN_AREA_MESSAGE, null); });
    }

    private void validateMaxCapacity(Integer load, Integer maxCapacity) {
        ValidationUtil.validateIsHigherThan(load, maxCapacity, new InternalServerErrorException(Constant.Center.MAX_CAPACITY_EXCEEDED_MESSAGE, null));
    }

    public void validateMaxCapacityOnUpdate(Integer load, Integer maxCapacity) {
        ValidationUtil.validateIsHigherThan(load, maxCapacity, new InternalServerErrorException(Constant.Center.MAX_CAPACITY_EXCEEDED_MESSAGE, null));
    }
}
