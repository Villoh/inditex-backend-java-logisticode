package com.hackathon.inditex.controller;

import com.hackathon.inditex.model.dto.ResponseMessageDTO;
import com.hackathon.inditex.model.dto.center.CenterCreationDTO;
import com.hackathon.inditex.model.dto.center.CenterDTO;
import com.hackathon.inditex.model.dto.center.UpdateCenterDTO;
import com.hackathon.inditex.service.CenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/centers")
@RequiredArgsConstructor
public class CenterController {

    private final CenterService centerService;

    @PostMapping()
    public ResponseEntity<ResponseMessageDTO> createCenter(@RequestBody CenterCreationDTO centerCreationDto){
        ResponseMessageDTO responseMessageDTO = centerService.createCenter(centerCreationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessageDTO);
    }

    @GetMapping()
    public ResponseEntity<List<CenterDTO>> getAllCenters(){
        List<CenterDTO> centerDtos = centerService.getAllCenters();
        return ResponseEntity.ok(centerDtos);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseMessageDTO> updateCenter(@PathVariable Long id, @RequestBody UpdateCenterDTO updateCenterDto){
        ResponseMessageDTO responseMessageDTO = centerService.updateCenter(id, updateCenterDto);
        return ResponseEntity.ok(responseMessageDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessageDTO> deleteCenter(@PathVariable Long id){
        ResponseMessageDTO responseMessageDTO = centerService.deleteCenter(id);
        return ResponseEntity.ok(responseMessageDTO);
    }
}
