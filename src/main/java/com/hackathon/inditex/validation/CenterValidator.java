package com.hackathon.inditex.validation;

import com.hackathon.inditex.exception.BadRequestException;
import com.hackathon.inditex.exception.InternalServerErrorException;
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
        validateCapacity(center.getCapacity());
        validateMaxCapacity(center.getCurrentLoad(), center.getMaxCapacity());
        validateCenterExistsByLatAndLong(center.getCoordinates());
    }

    /**
     * Validates the update of a center.
     *
     * @param centerUpdateDTO The DTO containing the updated information for the center.
     */
    public void validateUpdate(CenterUpdateDTO centerUpdateDTO) {
        Optional.ofNullable(centerUpdateDTO.getCapacity())
                .ifPresent(this::validateCapacity);
        Optional.ofNullable(centerUpdateDTO.getCoordinates())
                .ifPresent(this::validateCenterExistsByLatAndLong);
    }

    public void validateDelete(Long id){
        ValidationUtil.validateNotNullOrBlank(id, new InternalServerErrorException(Constant.Center.INVALID_INPUT_FIELDS, null));
        if(!centerRepository.existsById(id)){
            throw new InternalServerErrorException(Constant.Center.CENTER_NOT_FOUND, null);
        }
    }

    private void validateCenterExistsByLatAndLong(Coordinates coordinates) {
        ValidationUtil.validateNotNullOrBlank(coordinates, new InternalServerErrorException(Constant.Center.INVALID_INPUT_FIELDS, null));
        if (centerRepository.existsByCoordinates_LatitudeAndCoordinates_Longitude(coordinates.getLatitude(), coordinates.getLongitude())) {
            throw new InternalServerErrorException(Constant.Center.ALREADY_EXISTS_CENTER_IN_AREA_MESSAGE, null);
        }
    }

    private void validateMaxCapacity(Integer load, Integer maxCapacity) {
        ValidationUtil.validateNotNullOrBlankBulk(Arrays.asList(load, maxCapacity), new InternalServerErrorException(Constant.Center.INVALID_INPUT_FIELDS, null));
        ValidationUtil.validateIsHigherThan(load, maxCapacity, new InternalServerErrorException(Constant.Center.MAX_CAPACITY_EXCEEDED_MESSAGE, null));
    }

    public void validateMaxCapacityOnUpdate(Integer load, Integer maxCapacity){
        if (load != null && maxCapacity != null && load > maxCapacity){
            throw new InternalServerErrorException(Constant.Center.MAX_CAPACITY_EXCEEDED_MESSAGE, null);
        }
    }


    private void validateCapacity(String capacity) {
        ValidationUtil.validateNotNullOrBlank(capacity, new InternalServerErrorException(Constant.Center.INVALID_INPUT_FIELDS, null));
        ValidationUtil.validateRegex(capacity, "^[BMS]{1,3}$", new InternalServerErrorException(Constant.Center.UNRECOGNIZED_CAPACITY, null));
    }
}
