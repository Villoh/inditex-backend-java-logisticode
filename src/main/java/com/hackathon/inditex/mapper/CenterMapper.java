package com.hackathon.inditex.mapper;

import com.hackathon.inditex.model.dto.center.CenterCreationDTO;
import com.hackathon.inditex.model.dto.center.CenterDTO;
import com.hackathon.inditex.model.entity.Center;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Mapper class for converting between Center entity and DTOs.
 */
@Component
public class CenterMapper {

    /**
     * Converts a Center entity to a CenterDTO.
     *
     * @param center the Center entity to convert
     * @return the corresponding CenterDTO
     */
    public CenterDTO centerToCenterDto(Center center){
        return CenterDTO.builder()
                .id(center.getId())
                .name(center.getName())
                .capacity(center.getCapacity())
                .status(center.getStatus())
                .currentLoad(center.getCurrentLoad())
                .maxCapacity(center.getMaxCapacity())
                .coordinates(center.getCoordinates())
                .build();
    }

    /**
     * Converts a list of Center entities to a list of CenterDTOs.
     *
     * @param centers the list of Center entities to convert
     * @return a list of corresponding CenterDTOs
     */
    public List<CenterDTO> centersToCenterDtos(List<Center> centers){
        return centers.stream()
                .map(this::centerToCenterDto)
                .toList();
    }

    /**
     * Converts a CenterCreationDTO to a Center entity.
     *
     * @param centerDto the CenterCreationDTO to convert
     * @return the corresponding Center entity
     */
    public Center centerDtoToCenter(CenterCreationDTO centerDto){
        return Center.builder()
                .name(centerDto.getName())
                .capacity(centerDto.getCapacity())
                .status(centerDto.getStatus())
                .currentLoad(centerDto.getCurrentLoad())
                .maxCapacity(centerDto.getMaxCapacity())
                .coordinates(centerDto.getCoordinates())
                .build();
    }
}
