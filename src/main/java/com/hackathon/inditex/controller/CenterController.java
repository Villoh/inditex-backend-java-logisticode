package com.hackathon.inditex.controller;

import com.hackathon.inditex.model.dto.ResponseMessageDTO;
import com.hackathon.inditex.model.dto.center.CenterCreationDTO;
import com.hackathon.inditex.model.dto.center.CenterDTO;
import com.hackathon.inditex.model.dto.center.CenterUpdateDTO;
import com.hackathon.inditex.service.CenterService;
import com.hackathon.inditex.util.Constant;
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
@RequestMapping(Constant.Api.CENTERS_ENDPOINT)
@RequiredArgsConstructor
public class CenterController {

    private final CenterService centerService;

    @PostMapping
    public ResponseEntity<ResponseMessageDTO> createCenter(@RequestBody CenterCreationDTO centerCreationDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(centerService.createCenter(centerCreationDto));
    }

    @GetMapping
    public ResponseEntity<List<CenterDTO>> getAllCenters(){
        return ResponseEntity.ok(centerService.getAllCenters());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseMessageDTO> updateCenter(@PathVariable Long id, @RequestBody CenterUpdateDTO centerUpdateDto){
        return ResponseEntity.ok(centerService.updateCenter(id, centerUpdateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessageDTO> deleteCenter(@PathVariable Long id){
        return ResponseEntity.ok(centerService.deleteCenter(id));
    }
}
