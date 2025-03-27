package com.hackathon.inditex.service.impl;

import com.hackathon.inditex.exception.NotFoundException;
import com.hackathon.inditex.mapper.CenterMapper;
import com.hackathon.inditex.model.dto.ResponseMessageDTO;
import com.hackathon.inditex.model.dto.center.CenterCreationDTO;
import com.hackathon.inditex.model.dto.center.CenterDTO;
import com.hackathon.inditex.model.dto.center.CenterUpdateDTO;
import com.hackathon.inditex.model.entity.Center;
import com.hackathon.inditex.repository.CenterRepository;
import com.hackathon.inditex.service.CenterService;
import com.hackathon.inditex.util.Constant;
import com.hackathon.inditex.validation.CenterValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CenterServiceImpl implements CenterService {

    private final CenterRepository centerRepository;
    private final CenterMapper centerMapper;
    private final CenterValidator centerValidator;

    @Override
    public ResponseMessageDTO createCenter(CenterCreationDTO centerCreationDTO) {
        Center newCenter = centerMapper.centerDtoToCenter(centerCreationDTO);
        centerValidator.validateCreation(newCenter);
        centerRepository.save(newCenter);
        return ResponseMessageDTO.builder()
                .message(Constant.Center.SUCCESSFUL_CREATION_MESSAGE)
                .build();
    }

    @Override
    public List<CenterDTO> getAllCenters() {
        List<Center> centers = centerRepository.findAll();
        return centerMapper.centersToCenterDtos(centers);
    }

    @Override
    public ResponseMessageDTO updateCenter(Long id, CenterUpdateDTO centerUpdateDTO) {
        Center center = centerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Constant.Center.CENTER_NOT_FOUND, null));

        centerValidator.validateUpdate(centerUpdateDTO);

        Optional.ofNullable(centerUpdateDTO.getName()).ifPresent(center::setName);
        Optional.ofNullable(centerUpdateDTO.getCapacity()).ifPresent(center::setCapacity);
        Optional.ofNullable(centerUpdateDTO.getStatus()).ifPresent(center::setStatus);
        Optional.ofNullable(centerUpdateDTO.getMaxCapacity()).ifPresent(center::setMaxCapacity);
        Optional.ofNullable(centerUpdateDTO.getCoordinates()).ifPresent(center::setCoordinates);

        //Validate Max Capacity after updating it
        centerValidator.validateMaxCapacityOnUpdate(centerUpdateDTO.getCurrentLoad(), center.getMaxCapacity());
        // Update current load after validation
        Optional.ofNullable(centerUpdateDTO.getCurrentLoad()).ifPresent(center::setCurrentLoad);

        centerRepository.save(center);
        return ResponseMessageDTO.builder()
                .message(Constant.Center.UPDATED_CENTER)
                .build();
    }

    @Override
    public ResponseMessageDTO deleteCenter(Long id) {

        return ResponseMessageDTO.builder()
                .message(Constant.Center.DELETED_CENTER)
                .build();
    }
}
