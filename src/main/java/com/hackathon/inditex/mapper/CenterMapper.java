package com.hackathon.inditex.mapper;

import com.hackathon.inditex.model.dto.center.CenterCreationDTO;
import com.hackathon.inditex.model.dto.center.CenterDTO;
import com.hackathon.inditex.model.entity.Center;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CenterMapper {
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

    public List<CenterDTO> centersToCenterDtos(List<Center> centers){
        return centers.stream()
                .map(this::centerToCenterDto)
                .toList();
    }

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
