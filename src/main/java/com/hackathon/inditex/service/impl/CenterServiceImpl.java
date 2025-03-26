package com.hackathon.inditex.service.impl;

import com.hackathon.inditex.exception.BadRequestException;
import com.hackathon.inditex.exception.NotFoundException;
import com.hackathon.inditex.mapper.CenterMapper;
import com.hackathon.inditex.model.dto.Coordinates;
import com.hackathon.inditex.model.dto.ResponseMessageDTO;
import com.hackathon.inditex.model.dto.center.CenterCreationDTO;
import com.hackathon.inditex.model.dto.center.CenterDTO;
import com.hackathon.inditex.model.dto.center.UpdateCenterDTO;
import com.hackathon.inditex.model.entity.Center;
import com.hackathon.inditex.repository.CenterRepository;
import com.hackathon.inditex.service.CenterService;
import com.hackathon.inditex.util.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CenterServiceImpl implements CenterService {

    private final CenterRepository centerRepository;
    private final CenterMapper centerMapper;

    @Override
    public ResponseMessageDTO createCenter(CenterCreationDTO centerCreationDTO) {
        Center newCenter = centerMapper.centerDtoToCenter(centerCreationDTO);
        validateNewCenter(newCenter);
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
    public ResponseMessageDTO updateCenter(Long id, UpdateCenterDTO updateCenterDTO) {
        Center center = centerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Constant.Center.CENTER_NOT_FOUND, null));

        Optional.ofNullable(updateCenterDTO.getName()).ifPresent(center::setName);
        Optional.ofNullable(updateCenterDTO.getCapacity()).ifPresent(center::setCapacity);
        Optional.ofNullable(updateCenterDTO.getStatus()).ifPresent(center::setStatus);
        Optional.ofNullable(updateCenterDTO.getMaxCapacity()).ifPresent(center::setMaxCapacity);

        updateCurrentLoad(center, updateCenterDTO.getCurrentLoad());
        updateCoordinates(center, updateCenterDTO.getCoordinates());

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

    private void validateNewCenter(Center newCenter) {
        checkMaxCapacity(newCenter.getCurrentLoad(), newCenter.getMaxCapacity());
        checkDuplicateCenter(newCenter.getCoordinates().getLatitude(), newCenter.getCoordinates().getLongitude());
    }

    private void checkMaxCapacity(Integer centerLoad, Integer capacity) {
        Optional.ofNullable(centerLoad)
                .filter(load -> load > capacity)
                .ifPresent(e -> { throw new BadRequestException(Constant.Center.MAX_CAPACITY_EXCEEDED_MESSAGE, null);});
    }

    private void checkDuplicateCenter(Double latitude, Double longitude) {
        Optional.of(centerRepository.existsByCoordinates_LatitudeAndCoordinates_Longitude(latitude, longitude))
                .filter(Boolean::booleanValue)
                .ifPresent(e -> { throw new BadRequestException(Constant.Center.ALREADY_EXISTS_CENTER_IN_AREA_MESSAGE, null); });
    }

    private void updateCurrentLoad(Center center, Integer newLoad) {
        checkMaxCapacity(newLoad, center.getMaxCapacity());
        center.setCurrentLoad(newLoad);
    }

    private void updateCoordinates(Center center, Coordinates newCoordinates) {
        Optional.ofNullable(newCoordinates).ifPresent(coords -> {
            checkDuplicateCenter(coords.getLatitude(), coords.getLongitude());
            center.setCoordinates(coords);
        });
    }
}
