package com.hackathon.inditex.service;

import com.hackathon.inditex.model.dto.ResponseMessageDTO;
import com.hackathon.inditex.model.dto.center.CenterCreationDTO;
import com.hackathon.inditex.model.dto.center.CenterDTO;
import com.hackathon.inditex.model.dto.center.CenterUpdateDTO;

import java.util.List;

public interface CenterService {
    ResponseMessageDTO createCenter(CenterCreationDTO centerCreationDTO);
    List<CenterDTO> getAllCenters();
    ResponseMessageDTO updateCenter(Long id, CenterUpdateDTO centerUpdateDTO);
    ResponseMessageDTO deleteCenter(Long id);
}
